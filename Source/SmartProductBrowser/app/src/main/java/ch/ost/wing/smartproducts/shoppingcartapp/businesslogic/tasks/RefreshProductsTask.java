package ch.ost.wing.smartproducts.shoppingcartapp.businesslogic.tasks;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;

import ch.ost.wing.smartproducts.shoppingcartapp.businesslogic.ICallbackHandler;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.local.IProductRepository;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.local.entities.Product;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.IDownloadClient;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.IProductApiClient;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities.ContentResponse;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities.ProductDto;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities.ProductInfoDto;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities.ResponseTypes;

class RefreshProductsTask extends AsyncTask<Void, Void, Void> implements ITask {

    private final IProductApiClient _client;
    private final IProductRepository _repo;
    private final IDownloadClient _downloads;

    @Inject
    public RefreshProductsTask(IProductApiClient client, IProductRepository repo, IDownloadClient download){
        this._client = client;
        this._repo = repo;
        this._downloads = download;
    }

    private ICallbackHandler<Void> _callback;
    void setCallback(ICallbackHandler<Void> callback){
        this._callback = callback;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            ContentResponse<List<ProductInfoDto>> response = this._client.getAllProducts();
            if (!this.usable(response)) {
                return null;
            }
            this.updateProdcuts(response.getContent());
            return null;
        } catch (Throwable ex){
            ex.printStackTrace();
            return null;
        }
    }

    private void updateProdcuts(List<ProductInfoDto> content) {
        Set<UUID> ids = this.getExistingIds();
        for(ProductInfoDto info : content){
            try{
                ContentResponse<ProductDto> response = this._client.getDetailsOfProductById(info.getId());
                if(!this.usable(response)){
                    continue;
                }
                this.updateProductDetails(response.getContent());
                ids.remove(info.getId());
            } catch (Throwable ex){
                ex.printStackTrace();
            }
        }
        this.removeUnavailableProducts(ids);
    }

    private Set<UUID> getExistingIds(){
        Set<UUID> ids = new HashSet<>();
        for(Product dto : this._repo.getAll()){
            ids.add(dto.getId());
        }
        return ids;
    }

    private void updateProductDetails(ProductDto content) {
        Product product = this.toProduct(content);
        this._repo.update(product);
        Bitmap image = this.downloadImageOf(product);
        if(image == null) {
            return;
        }
        this._repo.storeImageOf(product.getId(), image);
    }

    private Bitmap downloadImageOf(Product product) {
        try {
            ContentResponse<Bitmap> response = this._downloads.getImageFrom(product.getImageUrl());
            if(!this.usable(response)){
                return null;
            }
            return response.getContent();
        } catch (Throwable ex){
            ex.printStackTrace();
            return null;
        }
    }

    private Product toProduct(ProductDto content) {
        Product product = new Product(content.getId(), content.getName(), content.getImageUrl());
        product.setArticleNumber(content.getArticleNumber());
        product.setPrice(content.getPrice());
        product.setWeight(content.getWeight());
        product.setRetailer(content.getRetailer());
        return product;
    }

    private void removeUnavailableProducts(Iterable<UUID> ids){
        for(UUID id : ids){
            try{
                this._repo.delete(id);
            } catch (Throwable ex){
                ex.printStackTrace();
            }
        }
    }

    private boolean usable(ContentResponse response){
        return response.getResponseType() == ResponseTypes.OK && response.hasContent();
    }

    @Override
    protected void onPostExecute(Void v){
        this._callback.handle(v);
    }

    @Override
    public void run() {
        this.execute();
    }
}
