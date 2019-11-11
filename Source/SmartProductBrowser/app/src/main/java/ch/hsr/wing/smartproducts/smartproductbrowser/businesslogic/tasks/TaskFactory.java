package ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.tasks;

import javax.inject.Inject;
import javax.inject.Provider;

import ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.ICallbackHandler;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local.entities.Product;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities.ResponseTypes;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.IApiClient;

public class TaskFactory implements ITaskFactory {

    private final Provider<LoadProductsTask> _loadProductsTask;
    private final Provider<RefreshProductsTask> _refreshProductsTask;

    @Inject
    public TaskFactory(Provider<LoadProductsTask> loadProductsTask,
                       Provider<RefreshProductsTask> refreshProductsTask){
        this._loadProductsTask = loadProductsTask;
        this._refreshProductsTask = refreshProductsTask;
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
    public ITask createRefreshProductsTask(ICallbackHandler<Void> callback) {
        RefreshProductsTask task = this._refreshProductsTask.get();
        task.setCallback(callback);
        return task;
    }
}
