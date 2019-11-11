package ch.hsr.wing.smartproducts.smartproductbrowser.util.settings;

import javax.inject.Singleton;

import ch.hsr.wing.smartproducts.smartproductbrowser.IApp;
import dagger.Module;
import dagger.Provides;

@Module
public class SettingsModule {

    @Provides
    @Singleton
    public AppSettings getAppSettingsWrapper(IApp app){
        return new AppSettings(app);
    }

    @Provides
    public IAppSettings getAppSettings(AppSettings settings){
        return settings;
    }

    @Provides
    public ConnectionSettings getConnecntionSettingsImpl(IAppSettings settings){
        return new ConnectionSettings(settings);
    }

    @Provides
    public IConnectionSettings getConnectionSettings(ConnectionSettings settings){
        return settings;
    }
}
