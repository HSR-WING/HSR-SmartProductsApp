package ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local;

import android.graphics.Bitmap;

import java.util.List;
import java.util.UUID;

import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local.entities.Product;

public interface IProductRepository {
    Iterable<Product> getAll();
    boolean exists(UUID productId);
    Product get(UUID productId);
    void update(Product product);
    boolean delete(UUID productId);

    Bitmap getImageOf(UUID productId);
    void storeImageOf(UUID productId, Bitmap image);
}
