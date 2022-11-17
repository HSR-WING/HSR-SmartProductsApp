package ch.ost.wing.smartproducts.shoppingcartapp.businesslogic.shoppingcart;

import ch.ost.wing.smartproducts.shoppingcartapp.entities.ShoppingCart;

public interface IDataReloadHandler {
    void onUpdate(ShoppingCart cart);
}
