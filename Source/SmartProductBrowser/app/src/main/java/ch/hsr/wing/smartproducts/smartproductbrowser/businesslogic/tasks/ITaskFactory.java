package ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.tasks;

import ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.ICallbackHandler;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local.entities.Product;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities.ResponseTypes;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.IApiClient;
import ch.hsr.wing.smartproducts.smartproductbrowser.entities.CartItem;
import ch.hsr.wing.smartproducts.smartproductbrowser.entities.ShoppingCart;

public interface ITaskFactory {
    ITask createPingTask(IApiClient client, ICallbackHandler<ResponseTypes> callback);
    ITask createLoadProductsTask(ICallbackHandler<Iterable<Product>> callback);
    ITask createRefreshProductsTask(ICallbackHandler<Void> callback);

    ITask createLoadShoppingCartItemsTask(ICallbackHandler<ShoppingCart> callback);
}
