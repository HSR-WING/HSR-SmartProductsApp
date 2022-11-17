package ch.ost.wing.smartproducts.shoppingcartapp.businesslogic.shoppingcart;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.local.IProductRepository;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.local.entities.Product;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities.DataDto;
import ch.ost.wing.smartproducts.shoppingcartapp.entities.CartItem;
import ch.ost.wing.smartproducts.shoppingcartapp.entities.ShoppingCart;

public class DefaultShoppingCartConverter implements IDataDtoConverter {

    private final IProductRepository _repo;

    @Inject
    public DefaultShoppingCartConverter(IProductRepository repo){
        this._repo = repo;
    }

    @Override
    public ShoppingCart toShoppingCart(DataDto data) {
        Iterable<CartItem> items = this.toItems(data.getData());
        return new ShoppingCart(items, data.getTimestamp());
    }

    private Iterable<CartItem> toItems(JsonObject data) {
        List<CartItem> items = new ArrayList<>();
        for(Map.Entry<String, JsonElement> entry : data.entrySet()){
            try {
                UUID id = UUID.fromString(entry.getKey());
                int amount = entry.getValue().getAsInt();
                Product p = this._repo.get(id);
                items.add(new CartItem(id, amount, p));
            } catch (Throwable t) {
                continue;
            }
        }
        return items;
    }
}
