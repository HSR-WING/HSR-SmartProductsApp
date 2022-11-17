package ch.ost.wing.smartproducts.shoppingcartapp.businesslogic.tasks;

import ch.ost.wing.smartproducts.shoppingcartapp.businesslogic.shoppingcart.IDataDtoConverter;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.local.IProductRepository;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.IDataApiClient;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.IDownloadClient;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.IProductApiClient;
import dagger.Module;
import dagger.Provides;

@Module
public class TaskModule {

    @Provides
    public LoadProductsTask createLoadProductsTask(IProductRepository repo){
        return new LoadProductsTask(repo);
    }

    @Provides
    public LoadProductTask createLoadProductTask(IProductRepository repo){
        return new LoadProductTask(repo);
    }

    @Provides
    public RefreshProductsTask createRefreshProductsTask(IProductApiClient client, IProductRepository repo, IDownloadClient download){
        return new RefreshProductsTask(client, repo, download);
    }

    @Provides
    public LoadShoppingCartItemsTask createLoadShoppingCartItemsTask(IDataApiClient client, IDataDtoConverter converter){
        return new LoadShoppingCartItemsTask(client, converter);
    }
}
