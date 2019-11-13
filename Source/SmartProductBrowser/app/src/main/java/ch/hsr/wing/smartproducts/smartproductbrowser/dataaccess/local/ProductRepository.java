package ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

import javax.inject.Inject;

import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local.entities.Product;
import ch.hsr.wing.smartproducts.smartproductbrowser.util.ImageUtil;

public class ProductRepository implements IProductRepository {
    private final AppDatabase _db;
    private final IFileSystem _fileSystem;

    @Inject
    public ProductRepository(AppDatabase db, IFileSystem fileSystem){
        this._db = db;
        this._fileSystem = fileSystem;
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
        if(product == null){
            return;
        }
        if(this.exists(product.getId())){
            this._db.products().update(product);
        } else {
            this._db.products().insert(product);
        }
    }

    @Override
    public boolean delete(UUID productId) {
        if(!this.exists(productId)){
           return false;
        }
        this._db.products().delete(Product.getDeletable(productId));
        return !this.exists(productId);
    }

    private static final String IMAGES_FOLDER = "ProductImages";
    private static final String IMAGE_FILE_EXTENSION = ".png";
    @Override
    public boolean hasImage(UUID productId) {
        try {
            String filename = this.toFilename(productId, IMAGE_FILE_EXTENSION);
            return this._fileSystem.exists(IMAGES_FOLDER, filename);
        } catch (Throwable ex){
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public Bitmap getImageOf(UUID productId) {
        String filename = this.toFilename(productId, IMAGE_FILE_EXTENSION);
        byte[] content = this._fileSystem.load(IMAGES_FOLDER, filename);
        return BitmapFactory.decodeByteArray(content,0,content.length);
    }

    @Override
    public void storeImageOf(UUID productId, Bitmap image) {
        try {
            String filename = this.toFilename(productId, IMAGE_FILE_EXTENSION);
            byte[] content = ImageUtil.toByteArray(image);
            this._fileSystem.store(IMAGES_FOLDER, filename, content);
        } catch (Throwable ex){
            ex.printStackTrace();
        }
    }

    private String toFilename(UUID id, String fileExtension){
        return id.toString() + fileExtension;
    }
}
