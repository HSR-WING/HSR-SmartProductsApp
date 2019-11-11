package ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local;

import androidx.room.Room;

import javax.inject.Singleton;

import ch.hsr.wing.smartproducts.smartproductbrowser.IApp;
import dagger.Module;
import dagger.Provides;

@Module
public class LocalDataAccessModule {

    @Provides
    @Singleton
    public AppDatabase getDatabase(IApp app){
        return Room.databaseBuilder(app.getAppContext(), AppDatabase.class, "SmartProductsDB").build();
    }
}
