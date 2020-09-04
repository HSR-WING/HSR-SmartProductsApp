package ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.local.entities.Product;

@Database(entities = {Product.class}, version = 1)
@TypeConverters(UUIDConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract IProductDao products();
}
