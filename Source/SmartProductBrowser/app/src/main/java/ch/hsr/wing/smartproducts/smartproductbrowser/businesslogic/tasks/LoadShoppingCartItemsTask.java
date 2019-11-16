package ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.tasks;

import android.os.AsyncTask;

import javax.inject.Inject;

import ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.ICallbackHandler;
import ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.IDataDtoConverter;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.IDataApiClient;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities.ContentResponse;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities.DataDto;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities.ResponseTypes;
import ch.hsr.wing.smartproducts.smartproductbrowser.entities.ShoppingCart;

class LoadShoppingCartItemsTask extends AsyncTask<Void, Void, ShoppingCart> implements ITask {

    private final IDataApiClient _client;
    private final IDataDtoConverter _converter;

    @Inject
    public LoadShoppingCartItemsTask(IDataApiClient client, IDataDtoConverter converter){
        this._client = client;
        this._converter = converter;
    }

    private ICallbackHandler<ShoppingCart> _callback;
    void setCallback(ICallbackHandler<ShoppingCart> callback){
        this._callback = callback;
    }

    @Override
    protected ShoppingCart doInBackground(Void... voids) {
        try{
            ContentResponse<DataDto> data = this._client.getLatest();
            if(!this.success(data)){
                return null;
            }
            if(!data.hasContent()){
                return new ShoppingCart();
            }
            return this._converter.toShoppingCart(data.getContent());
        } catch (Exception ex){
            return null;
        }
    }

    private boolean success(ContentResponse<DataDto> data){
        return data.getResponseType() == ResponseTypes.OK;
    }

    @Override
    protected void onPostExecute(ShoppingCart cart){
        this._callback.handle(cart);
    }

    @Override
    public void run() {
        this.execute();
    }
}
