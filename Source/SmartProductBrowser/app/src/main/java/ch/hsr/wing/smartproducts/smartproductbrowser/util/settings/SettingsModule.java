package ch.hsr.wing.smartproducts.smartproductbrowser.util.settings;

import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SettingsModule {

    private final SharedPreferences _settings;

    public SettingsModule(SharedPreferences settings){
        this._settings = settings;
    }

    @Provides
    @Singleton
    public AppSettings getAppSettingsWrapper(){
        return new AppSettings(this._settings);
    }

    @Provides
    public IAppSettings getAppSettings(AppSettings settings){
        return settings;
    }

    /*@Provides
    public ConnectionSettings getConnecntionSettingsImpl(IAppSettings settings){
        return new ConnectionSettings(settings);
    }*/

    @Provides
    public IConnectionSettings getConnectionSettings(ConnectionSettings settings){
        return settings;
    }
}
