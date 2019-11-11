package ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Picture;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.IOError;
import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

import javax.inject.Provider;

import ch.hsr.wing.smartproducts.smartproductbrowser.IApp;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local.entities.Product;
import ch.hsr.wing.smartproducts.smartproductbrowser.util.ImageUtil;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ProductRepositoryTest {

    private AppDatabase getDb(){
        return Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), AppDatabase.class).build();
    }

    @Test
    public void test_ProductRepository_GetAll_Empty(){
        ProductRepository repo = new ProductRepository(this.getDb(), mock(IFileSystem.class));

        Iterable<Product> products = repo.getAll();

        assertFalse(products.iterator().hasNext());
    }

    @Test
    public void test_ProductRepository_GetAll(){
        AppDatabase db = this.getDb();

        UUID id = UUID.randomUUID();
        Product dbProduct = new Product(id, "Coffee", "http://coff.ee");
        db.products().insert(dbProduct);

        ProductRepository repo = new ProductRepository(db, mock(IFileSystem.class));

        Iterable<Product> products = repo.getAll();
        Iterator<Product> it = products.iterator();

        assertTrue(it.hasNext());
        Product product = it.next();
        assertEquals(id, product.getId());
        assertEquals("Coffee", product.getName());
        assertEquals("http://coff.ee", product.getImageUrl());
    }

    @Test
    public void test_ProductRepository_Exists_True(){
        AppDatabase db = this.getDb();

        UUID id = UUID.randomUUID();
        Product dbProduct = new Product(id, "Coffee", "http://coff.ee");
        db.products().insert(dbProduct);

        ProductRepository repo = new ProductRepository(db, mock(IFileSystem.class));

        boolean exists = repo.exists(id);
        assertTrue(exists);
    }

    @Test
    public void test_ProductRepository_Exists_False(){
        AppDatabase db = this.getDb();

        UUID id = UUID.randomUUID();
        Product dbProduct = new Product(id, "Coffee", "http://coff.ee");
        db.products().insert(dbProduct);

        ProductRepository repo = new ProductRepository(db, mock(IFileSystem.class));

        boolean exists = repo.exists(UUID.randomUUID());
        assertFalse(exists);
    }

    @Test
    public void test_ProductRepository_Get_ById(){
        AppDatabase db = this.getDb();

        UUID id = UUID.randomUUID();
        Product dbProduct = new Product(id, "Coffee", "http://coff.ee");
        db.products().insert(dbProduct);
        db.products().insert(new Product(UUID.randomUUID(), "Chips", ""));

        ProductRepository repo = new ProductRepository(db, mock(IFileSystem.class));

        Product product = repo.get(id);

        assertNotNull(product);
        assertEquals(id, product.getId());
        assertEquals("Coffee", product.getName());
        assertEquals("http://coff.ee", product.getImageUrl());
    }

    @Test
    public void test_ProductRepository_Get_ById_NotExisting(){
        AppDatabase db = this.getDb();

        UUID id = UUID.randomUUID();
        Product dbProduct = new Product(id, "Coffee", "http://coff.ee");
        db.products().insert(dbProduct);

        ProductRepository repo = new ProductRepository(db, mock(IFileSystem.class));

        Product product = repo.get(UUID.randomUUID());

        assertNull(product);
    }

    @Test
    public void test_ProductRepository_Update_Null(){
        AppDatabase db = this.getDb();

        ProductRepository repo = new ProductRepository(db, mock(IFileSystem.class));

        repo.update(null);

        assertTrue(db.products().getAll().isEmpty());
    }

    @Test
    public void test_ProductRepository_Update_Existing(){
        AppDatabase db = this.getDb();

        UUID id = UUID.randomUUID();
        Product dbProduct = new Product(id, "Coffee", "http://coff.ee");
        db.products().insert(dbProduct);

        ProductRepository repo = new ProductRepository(db, mock(IFileSystem.class));

        Product product = new Product(id, "Coffee", "http://coff.ee");
        product.setArticleNumber("co-0042");
        product.setPrice(42.21);

        repo.update(product);

        dbProduct = db.products().getById(id).get(0);

        assertEquals("co-0042", dbProduct.getArticleNumber());
        assertEquals(42.21, dbProduct.getPrice(), .0001);
    }

    @Test
    public void test_ProductRepository_Update_New(){
        AppDatabase db = this.getDb();

        ProductRepository repo = new ProductRepository(db, mock(IFileSystem.class));

        UUID id = UUID.randomUUID();
        Product product = new Product(id, "Chips", "");
        product.setArticleNumber("ch-1337");
        product.setWeight(1337);

        repo.update(product);

        Product dbProduct = db.products().getById(id).get(0);

        assertEquals("Chips", dbProduct.getName());
        assertEquals("ch-1337", dbProduct.getArticleNumber());
        assertEquals(1337, dbProduct.getWeight(), .0001);
    }

    @Test
    public void test_ProductRepository_Delete_NonExisting(){
        AppDatabase db = this.getDb();

        ProductRepository repo = new ProductRepository(db, mock(IFileSystem.class));

        boolean success = repo.delete(UUID.randomUUID());

        assertFalse(success);
    }

    @Test
    public void test_ProductRepository_Delete_Existing(){
        AppDatabase db = this.getDb();

        UUID id = UUID.randomUUID();
        Product dbProduct = new Product(id, "Coffee", "http://coff.ee");
        db.products().insert(dbProduct);

        ProductRepository repo = new ProductRepository(db, mock(IFileSystem.class));

        boolean success = repo.delete(id);

        assertTrue(success);
        assertTrue(db.products().getAll().isEmpty());
    }

    @Test
    public void test_ProductRepository_GetImage() throws IOException {

        IFileSystem fileSystem = mock(IFileSystem.class);
        UUID id = UUID.randomUUID();
        String imageName = id.toString() + ".png";
        Bitmap img = Bitmap.createBitmap(1,1, Bitmap.Config.ARGB_8888);
        img.setPixel(0,0, Color.BLUE);
        byte[] imgBytes = ImageUtil.toByteArray(img);
        when(fileSystem.load("ProductImages", imageName)).thenReturn(imgBytes);

        ProductRepository repo = new ProductRepository(mock(AppDatabase.class), fileSystem);

        Bitmap image = repo.getImageOf(id);

        assertNotNull(image);
        verify(fileSystem).load("ProductImages", imageName);
    }

    @Test
    public void test_ProductRepository_StoreImage(){
        IFileSystem fileSystem = mock(IFileSystem.class);
        UUID id = UUID.randomUUID();
        String imageName = id.toString() + ".png";

        ProductRepository repo = new ProductRepository(mock(AppDatabase.class), fileSystem);
        Bitmap bitmap = Bitmap.createBitmap(1,1, Bitmap.Config.ARGB_8888);
        bitmap.setPixel(0,0, Color.RED);
        repo.storeImageOf(id, bitmap);

        verify(fileSystem).store(eq("ProductImages"), eq(imageName), any(byte[].class));
    }

    private IApp getApp(){
        return new IApp() {
            @Override
            public Context getAppContext() {
                return ApplicationProvider.getApplicationContext();
            }

            @Override
            public SharedPreferences getSettings() {
                return null;
            }
        };
    }

    @Test
    public void test_ProductRepository_StoreAndLoadImage(){
        ProductRepository repo = new ProductRepository(mock(AppDatabase.class), new FileSystem(this.getApp()));
        Bitmap bitmap = Bitmap.createBitmap(1,1, Bitmap.Config.ARGB_8888);
        bitmap.setPixel(0,0, Color.GREEN);

        UUID id = UUID.randomUUID();

        repo.storeImageOf(id, bitmap);

        Bitmap result = repo.getImageOf(id);

        assertEquals(Color.GREEN, result.getPixel(0,0));
    }
}
