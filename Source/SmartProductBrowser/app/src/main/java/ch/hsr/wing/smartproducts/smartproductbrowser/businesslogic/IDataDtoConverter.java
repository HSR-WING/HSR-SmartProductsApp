package ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic;

import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities.DataDto;
import ch.hsr.wing.smartproducts.smartproductbrowser.entities.ShoppingCart;

public interface IDataDtoConverter {
    ShoppingCart toShoppingCart(DataDto data);
}
