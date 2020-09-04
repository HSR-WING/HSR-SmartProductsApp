package ch.ost.wing.smartproducts.smartproductbrowser.businesslogic.tasks;

import android.os.AsyncTask;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import ch.ost.wing.smartproducts.smartproductbrowser.businesslogic.ICallbackHandler;
import ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.local.IProductRepository;
import ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.local.entities.Product;

class LoadProductsTask extends AsyncTask<Void, Void, Iterable<Product>> implements ITask {

    private final IProductRepository _repo;

    @Inject
    public LoadProductsTask(IProductRepository repo){
        this._repo = repo;
    }

    private ICallbackHandler<Iterable<Product>> _callback;
    void setCallback(ICallbackHandler<Iterable<Product>> callback){
        this._callback = callback;
    }

    @Override
    protected Iterable<Product> doInBackground(Void... voids) {
        List<Product> products = new LinkedList<>();
        for(Product p : this._repo.getAll()){
           products.add(p);
        }
        return products;
    }

    @Override
    protected void onPostExecute(Iterable<Product> products){
        this._callback.handle(products);
    }

    @Override
    public void run() {
        this.execute();
    }
}
