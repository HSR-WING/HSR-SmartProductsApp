package ch.hsr.wing.smartproducts.smartproductbrowser.di;

import javax.inject.Singleton;

import ch.hsr.wing.smartproducts.smartproductbrowser.util.settings.SettingsModule;
import ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels.ProductCatalogViewModelModule;
import ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels.SettingsViewModelModule;
import ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels.ShoppingCartViewModelModule;
import ch.hsr.wing.smartproducts.smartproductbrowser.views.settings.ConnectionSettingsFragment;
import dagger.Component;

@Singleton
@Component(modules = {
        SettingsModule.class,
        SettingsViewModelModule.class
        })
public interface DIComponent {
    void inject(ConnectionSettingsFragment fragment);
}
