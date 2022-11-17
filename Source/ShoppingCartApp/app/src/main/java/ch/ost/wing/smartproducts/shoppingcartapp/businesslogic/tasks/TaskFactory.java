package ch.ost.wing.smartproducts.shoppingcartapp.businesslogic.tasks;

import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Provider;

import ch.ost.wing.smartproducts.shoppingcartapp.businesslogic.ICallbackHandler;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.local.entities.Product;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities.ResponseTypes;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.IApiClient;
import ch.ost.wing.smartproducts.shoppingcartapp.entities.ShoppingCart;

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
