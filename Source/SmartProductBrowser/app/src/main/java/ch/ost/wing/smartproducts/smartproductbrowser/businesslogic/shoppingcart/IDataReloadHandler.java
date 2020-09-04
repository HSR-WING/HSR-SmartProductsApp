package ch.ost.wing.smartproducts.smartproductbrowser.businesslogic.shoppingcart;

import ch.ost.wing.smartproducts.smartproductbrowser.entities.ShoppingCart;

public interface IDataReloadHandler {
    void onUpdate(ShoppingCart cart);
}
