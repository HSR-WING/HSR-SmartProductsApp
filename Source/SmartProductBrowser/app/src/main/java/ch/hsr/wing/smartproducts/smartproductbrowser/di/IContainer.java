package ch.hsr.wing.smartproducts.smartproductbrowser.di;

import javax.inject.Singleton;

import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.DataAccessModule;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.HttpModule;
import ch.hsr.wing.smartproducts.smartproductbrowser.util.settings.SettingsModule;
import ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels.ViewModelModule;
import ch.hsr.wing.smartproducts.smartproductbrowser.views.ProductCatalogFragment;
import ch.hsr.wing.smartproducts.smartproductbrowser.views.ShoppingCartFragment;
import ch.hsr.wing.smartproducts.smartproductbrowser.views.settings.ConnectionSettingsFragment;
import dagger.Component;

@Singleton
@Component(modules = {
        SettingsModule.class,
        ViewModelModule.class,
        DataAccessModule.class,
        HttpModule.class})
public interface IContainer {
    void inject(ConnectionSettingsFragment fragment);
    void inject(ProductCatalogFragment fragment);
    void inject(ShoppingCartFragment fragment);
}
