package ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local;

import android.graphics.Bitmap;

import java.util.Iterator;
import java.util.UUID;

import javax.inject.Inject;

import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local.entities.Product;

public class ProductRepository implements IProductRepository {
    private final AppDatabase _db;

    @Inject
    public ProductRepository(AppDatabase db){
        this._db = db;
    }

    @Override
    public Iterable<Product> getAll() {
        return this._db.products().getAll();
    }

    @Override
    public boolean exists(UUID productId) {
        Iterable<Product> products = this._db.products().getById(productId);
        return products.iterator().hasNext();
    }

    @Override
    public Product get(UUID productId) {
        Iterable<Product> products = this._db.products().getById(productId);
        Iterator<Product> it = products.iterator();
        if(it.hasNext()){
            return it.next();
        }
        return null;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public boolean delete(UUID productId) {
        return false;
    }

    @Override
    public Bitmap getImageOf(UUID productId) {
        return null;
    }

    @Override
    public void storeImageOf(UUID productId, Bitmap image) {

    }
}
