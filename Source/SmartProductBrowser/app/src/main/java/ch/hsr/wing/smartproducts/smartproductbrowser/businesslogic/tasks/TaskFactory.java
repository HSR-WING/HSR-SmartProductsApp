package ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.tasks;

import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Provider;

import ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.ICallbackHandler;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local.entities.Product;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities.ResponseTypes;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.IApiClient;
import ch.hsr.wing.smartproducts.smartproductbrowser.entities.CartItem;
import ch.hsr.wing.smartproducts.smartproductbrowser.entities.ShoppingCart;
import dagger.Provides;

public class TaskFactory implements ITaskFactory {

    private final Provider<LoadProductsTask> _loadProductsTask;
    private final Provider<LoadProductTask> _loadProductTask;
    private final Provider<RefreshProductsTask> _refreshProductsTask;
    private final Provider<LoadShoppingCartItemsTask> _loadShoppingCartItemsTask;

    @Inject
    public TaskFactory(Provider<LoadProductsTask> loadProductsTask,
                       Provider<LoadProductTask> loadProductTask,
                       Provider<RefreshProductsTask> refreshProductsTask,
                       Provider<LoadShoppingCartItemsTask> loadShoppingCartItemsTask){
        this._loadProductsTask = loadProductsTask;
        this._loadProductTask = loadProductTask;
        this._refreshProductsTask = refreshProductsTask;
        this._loadShoppingCartItemsTask = loadShoppingCartItemsTask;
    }

    @Override
    public ITask createPingTask(IApiClient client, ICallbackHandler<ResponseTypes> callback) {
        return new ApiPingTask(client, callback);
    }

    @Override
    public ITask createLoadProductsTask(ICallbackHandler<Iterable<Product>> callback) {
        LoadProductsTask task = this._loadProductsTask.get();
        task.setCallback(callback);
        return task;
    }

    @Override
    public IArgTask<UUID> createLoadProductTask(ICallbackHandler<Product> callback) {
        LoadProductTask task = this._loadProductTask.get();
        task.setCallback(callback);
        return task;
    }

    @Override
    public ITask createRefreshProductsTask(ICallbackHandler<Void> callback) {
        RefreshProductsTask task = this._refreshProductsTask.get();
        task.setCallback(callback);
        return task;
    }

    @Override
    public ITask createLoadShoppingCartItemsTask(ICallbackHandler<ShoppingCart> callback) {
        LoadShoppingCartItemsTask task = this._loadShoppingCartItemsTask.get();
        task.setCallback(callback);
        return task;
    }
}
