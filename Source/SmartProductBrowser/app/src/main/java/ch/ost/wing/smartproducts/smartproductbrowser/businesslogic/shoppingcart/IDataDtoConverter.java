package ch.ost.wing.smartproducts.smartproductbrowser.businesslogic.shoppingcart;

import ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities.DataDto;
import ch.ost.wing.smartproducts.smartproductbrowser.entities.ShoppingCart;

public interface IDataDtoConverter {
    ShoppingCart toShoppingCart(DataDto data);
}
