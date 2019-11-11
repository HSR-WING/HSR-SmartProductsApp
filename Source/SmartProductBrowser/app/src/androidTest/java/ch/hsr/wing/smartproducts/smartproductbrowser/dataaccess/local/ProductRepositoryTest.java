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
}
