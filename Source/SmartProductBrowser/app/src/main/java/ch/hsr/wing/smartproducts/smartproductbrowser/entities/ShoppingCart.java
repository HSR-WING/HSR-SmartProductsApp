package ch.hsr.wing.smartproducts.smartproductbrowser.entities;

import java.util.ArrayList;
import java.util.Date;

public class ShoppingCart {

    private final Date _timestamp;
    private final Iterable<CartItem> _items;

    public ShoppingCart(){
        this._items = new ArrayList<>();
        this._timestamp = new Date();
    }

    public ShoppingCart(Iterable<CartItem> items, Date timestamp){
        this._timestamp = timestamp;
        this._items = items;
    }

    public Iterable<CartItem> getItems(){
        return this._items;
    }

    public Date getTimestamp(){
        return this._timestamp;
    }
}
