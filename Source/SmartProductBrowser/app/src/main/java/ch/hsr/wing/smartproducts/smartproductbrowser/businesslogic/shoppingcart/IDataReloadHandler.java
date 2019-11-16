package ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.shoppingcart;

import ch.hsr.wing.smartproducts.smartproductbrowser.entities.ShoppingCart;

public interface IDataReloadHandler {
    void onUpdate(ShoppingCart cart);
}
