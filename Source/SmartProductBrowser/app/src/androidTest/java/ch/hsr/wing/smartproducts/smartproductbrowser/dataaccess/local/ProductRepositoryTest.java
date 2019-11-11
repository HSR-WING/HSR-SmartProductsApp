package ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Iterator;
import java.util.UUID;

import javax.inject.Provider;

import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local.entities.Product;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ProductRepositoryTest {

    private AppDatabase getDb(){
        return Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), AppDatabase.class).build();
    }

    @Test
    public void test_ProductRepository_GetAll_Empty(){
        ProductRepository repo = new ProductRepository(this.getDb());

        Iterable<Product> products = repo.getAll();

        assertFalse(products.iterator().hasNext());
    }

    @Test
    public void test_ProductRepository_GetAll(){
        AppDatabase db = this.getDb();

        UUID id = UUID.randomUUID();
        Product dbProduct = new Product(id, "Coffee", "http://coff.ee");
        db.products().insert(dbProduct);

        ProductRepository repo = new ProductRepository(db);

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

        ProductRepository repo = new ProductRepository(db);

        boolean exists = repo.exists(id);
        assertTrue(exists);
    }

    @Test
    public void test_ProductRepository_Exists_False(){
        AppDatabase db = this.getDb();

        UUID id = UUID.randomUUID();
        Product dbProduct = new Product(id, "Coffee", "http://coff.ee");
        db.products().insert(dbProduct);

        ProductRepository repo = new ProductRepository(db);

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

        ProductRepository repo = new ProductRepository(db);

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

        ProductRepository repo = new ProductRepository(db);

        Product product = repo.get(UUID.randomUUID());

        assertNull(product);
    }

    @Test
    public void test_ProductRepository_Update_Null(){
        AppDatabase db = this.getDb();

        ProductRepository repo = new ProductRepository(db);

        repo.update(null);

        assertTrue(db.products().getAll().isEmpty());
    }

    @Test
    public void test_ProductRepository_Update_Existing(){
        AppDatabase db = this.getDb();

        UUID id = UUID.randomUUID();
        Product dbProduct = new Product(id, "Coffee", "http://coff.ee");
        db.products().insert(dbProduct);

        ProductRepository repo = new ProductRepository(db);

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

        ProductRepository repo = new ProductRepository(db);

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

        ProductRepository repo = new ProductRepository(db);

        boolean success = repo.delete(UUID.randomUUID());

        assertFalse(success);
    }

    @Test
    public void test_ProductRepository_Delete_Existing(){
        AppDatabase db = this.getDb();

        UUID id = UUID.randomUUID();
        Product dbProduct = new Product(id, "Coffee", "http://coff.ee");
        db.products().insert(dbProduct);

        ProductRepository repo = new ProductRepository(db);

        boolean success = repo.delete(id);

        assertTrue(success);
        assertTrue(db.products().getAll().isEmpty());
    }
}
