package ch.hsr.wing.smartproducts.smartproductbrowser.di;

import javax.inject.Singleton;

import ch.hsr.wing.smartproducts.smartproductbrowser.util.settings.SettingsModule;
import ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels.ViewModelModule;
import dagger.Component;

@Singleton
@Component(modules = {SettingsModule.class, ViewModelModule.class})
public interface DIComponent {
    void inject(Object obj);
}
