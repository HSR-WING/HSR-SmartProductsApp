package ch.ost.wing.smartproducts.shoppingcartapp.businesslogic.tasks;

import android.os.AsyncTask;

import java.util.UUID;

import javax.inject.Inject;

import ch.ost.wing.smartproducts.shoppingcartapp.businesslogic.ICallbackHandler;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.local.IProductRepository;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.local.entities.Product;

class LoadProductTask extends AsyncTask<UUID, Void, Product> implements IArgTask<UUID> {

    private final IProductRepository _repo;

    @Inject
    public LoadProductTask(IProductRepository repo){
        this._repo = repo;
    }

    private ICallbackHandler<Product> _callback;
    void setCallback(ICallbackHandler<Product> callback){
        this._callback = callback;
    }

    @Override
    protected Product doInBackground(UUID... uuids) {
        return this._repo.get(uuids[0]);
    }

    @Override
    protected void onPostExecute(Product product){
        this._callback.handle(product);
    }

    @Override
    public void run(UUID arg) {
        this.execute(arg);
    }
}
