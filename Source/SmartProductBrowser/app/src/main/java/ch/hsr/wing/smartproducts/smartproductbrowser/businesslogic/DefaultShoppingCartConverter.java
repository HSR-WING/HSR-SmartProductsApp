package ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic;

import javax.inject.Inject;

import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities.DataDto;
import ch.hsr.wing.smartproducts.smartproductbrowser.entities.ShoppingCart;

public class DefaultShoppingCartConverter implements IDataDtoConverter {

    @Inject
    public DefaultShoppingCartConverter(){

    }

    @Override
    public ShoppingCart toShoppingCart(DataDto data) {

        return new ShoppingCart();
    }
}
