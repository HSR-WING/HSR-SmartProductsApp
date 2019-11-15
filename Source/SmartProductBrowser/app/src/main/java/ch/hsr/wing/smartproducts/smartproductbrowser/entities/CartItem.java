package ch.hsr.wing.smartproducts.smartproductbrowser.entities;

import java.util.UUID;

public class CartItem {
    private final UUID _id;
    private final int _number;

    public CartItem(UUID id, int number){
        this._id = id;
        this._number = number;
    }

    public UUID getId(){
        return this._id;
    }

    public int getNumber(){
        return this._number;
    }
}
