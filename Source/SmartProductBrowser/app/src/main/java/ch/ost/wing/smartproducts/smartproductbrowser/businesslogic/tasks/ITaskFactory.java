package ch.ost.wing.smartproducts.smartproductbrowser.businesslogic.tasks;

import java.util.UUID;

import ch.ost.wing.smartproducts.smartproductbrowser.businesslogic.ICallbackHandler;
import ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.local.entities.Product;
import ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities.ResponseTypes;
import ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.remote.IApiClient;
import ch.ost.wing.smartproducts.smartproductbrowser.entities.ShoppingCart;

public interface ITaskFactory {
    ITask createPingTask(IApiClient client, ICallbackHandler<ResponseTypes> callback);
    ITask createLoadProductsTask(ICallbackHandler<Iterable<Product>> callback);
    IArgTask<UUID> createLoadProductTask(ICallbackHandler<Product> callback);
    ITask createRefreshProductsTask(ICallbackHandler<Void> callback);

    ITask createLoadShoppingCartItemsTask(ICallbackHandler<ShoppingCart> callback);
}
