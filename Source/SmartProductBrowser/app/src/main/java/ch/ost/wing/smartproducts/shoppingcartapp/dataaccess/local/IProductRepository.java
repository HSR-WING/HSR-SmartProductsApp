package ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.local;

import android.graphics.Bitmap;

import java.util.UUID;

import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.local.entities.Product;

public interface IProductRepository {
    Iterable<Product> getAll();
    boolean exists(UUID productId);
    Product get(UUID productId);
    void update(Product product);
    boolean delete(UUID productId);

    boolean hasImage(UUID productId);
    Bitmap getImageOf(UUID productId);
    void storeImageOf(UUID productId, Bitmap image);
}
