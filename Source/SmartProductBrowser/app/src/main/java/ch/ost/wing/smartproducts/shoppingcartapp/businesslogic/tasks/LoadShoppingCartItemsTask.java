package ch.ost.wing.smartproducts.shoppingcartapp.businesslogic.tasks;

import android.os.AsyncTask;

import javax.inject.Inject;

import ch.ost.wing.smartproducts.shoppingcartapp.businesslogic.ICallbackHandler;
import ch.ost.wing.smartproducts.shoppingcartapp.businesslogic.shoppingcart.IDataDtoConverter;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.IDataApiClient;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities.ContentResponse;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities.DataDto;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities.ResponseTypes;
import ch.ost.wing.smartproducts.shoppingcartapp.entities.ShoppingCart;

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
