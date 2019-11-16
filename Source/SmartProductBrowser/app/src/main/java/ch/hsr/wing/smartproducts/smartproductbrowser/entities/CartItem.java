package ch.hsr.wing.smartproducts.smartproductbrowser.entities;

import java.util.UUID;

import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local.entities.Product;

public class CartItem {
    private final UUID _id;
    private final int _number;
    private final Product _product;

    public CartItem(UUID id, int number, Product product){
        this._id = id;
        this._number = number;
        this._product = product;
    }

    public UUID getId(){
        return this._id;
    }

    public int getNumber(){
        return this._number;
    }
    public Product getProduct(){
        return this._product;
    }
}
