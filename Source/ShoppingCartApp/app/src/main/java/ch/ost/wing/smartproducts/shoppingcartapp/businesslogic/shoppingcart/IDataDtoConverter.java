package ch.ost.wing.smartproducts.shoppingcartapp.businesslogic.shoppingcart;

import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities.DataDto;
import ch.ost.wing.smartproducts.shoppingcartapp.entities.ShoppingCart;

public interface IDataDtoConverter {
    ShoppingCart toShoppingCart(DataDto data);
}
