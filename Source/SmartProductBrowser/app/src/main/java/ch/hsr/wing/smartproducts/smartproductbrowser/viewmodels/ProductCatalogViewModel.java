package ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.ICallbackHandler;
import ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.tasks.ITaskFactory;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local.entities.Product;

public class ProductCatalogViewModel extends BaseViewModel {

    private final ITaskFactory _tasks;

    @Inject
    public ProductCatalogViewModel(ITaskFactory tasks){
        this._tasks = tasks;
    }

    @Override
    protected void onInit() {
        this.loadStoredProducts(true);
    }

    private void loadStoredProducts(final boolean refresh) {
        this._tasks.createLoadProductsTask(new ICallbackHandler<Iterable<Product>>() {
            @Override
            public void handle(Iterable<Product> result) {
                for(IAdapterBinding<Product> binding : _bindings){
                    binding.refresh(result);
                }
                if(refresh) {
                    refreshProducts();
                }
            }
        }).run();
    }

    private void refreshProducts(){
        this._tasks.createRefreshProductsTask(new ICallbackHandler<Void>() {
            @Override
            public void handle(Void result) {
                loadStoredProducts(false);
            }
        }).run();
    }

    private final Set<IAdapterBinding<Product>> _bindings = new HashSet<>();
    public void bind(IAdapterBinding<Product> binding){
        if(binding == null){
            return;
        }
        this._bindings.add(binding);
    }

    public void unbind(IAdapterBinding<Product> binding){
        if(binding == null){
            return;
        }
        this._bindings.remove(binding);
    }

    @Override
    protected void onDispose() {

    }
}
