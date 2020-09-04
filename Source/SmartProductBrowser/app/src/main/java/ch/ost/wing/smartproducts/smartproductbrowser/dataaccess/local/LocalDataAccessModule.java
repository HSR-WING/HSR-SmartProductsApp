package ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.local;

import androidx.room.Room;

import javax.inject.Singleton;

import ch.ost.wing.smartproducts.smartproductbrowser.IApp;
import dagger.Module;
import dagger.Provides;

@Module
public class LocalDataAccessModule {

    @Provides
    @Singleton
    public AppDatabase getDatabase(IApp app){
        return Room.databaseBuilder(app.getAppContext(), AppDatabase.class, "SmartProductsDB").build();
    }

    @Provides
    public IFileSystem getFileSystem(IApp app){
        return new FileSystem(app);
    }

    @Provides
    public IProductRepository getProductRepo(AppDatabase db, IFileSystem fileSystem){
        return new ProductRepository(db, fileSystem);
    }
}
