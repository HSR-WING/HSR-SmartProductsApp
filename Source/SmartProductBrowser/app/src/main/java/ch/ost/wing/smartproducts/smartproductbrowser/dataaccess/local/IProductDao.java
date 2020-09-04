package ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import java.util.UUID;

import ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.local.entities.Product;

@Dao
public interface IProductDao {
    @Query("SELECT * FROM Products")
    List<Product> getAll();

    @Query("SELECT * from Products where Id = :id LIMIT 1")
    List<Product> getById(UUID id);

    @Insert
    void insert(Product... products);

    @Update
    void update(Product... products);

    @Delete
    void delete(Product... products);
}
