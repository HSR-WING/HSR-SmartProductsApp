package ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalDataAccessModule {

    private final Context _appContext;

    public LocalDataAccessModule(Context appContext){
        this._appContext = appContext;
    }

    @Provides
    @Singleton
    public AppDatabase getDatabase(){
        return Room.databaseBuilder(this._appContext, AppDatabase.class, "SmartProductsDB").build();
    }
}
