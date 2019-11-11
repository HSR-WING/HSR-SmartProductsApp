package ch.hsr.wing.smartproducts.smartproductbrowser.di;

import javax.inject.Singleton;

import ch.hsr.wing.smartproducts.smartproductbrowser.AppModule;
import ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.BusinessModule;
import ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.tasks.TaskModule;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local.LocalDataAccessModule;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.RemoteDataAccessModule;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.HttpModule;
import ch.hsr.wing.smartproducts.smartproductbrowser.util.settings.SettingsModule;
import ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels.ItemViewModelModule;
import ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels.ViewModelModule;
import ch.hsr.wing.smartproducts.smartproductbrowser.views.ProductCatalogFragment;
import ch.hsr.wing.smartproducts.smartproductbrowser.views.ShoppingCartFragment;
import ch.hsr.wing.smartproducts.smartproductbrowser.views.adapters.AdapterModule;
import ch.hsr.wing.smartproducts.smartproductbrowser.views.settings.ConnectionSettingsFragment;
import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        SettingsModule.class,
        AdapterModule.class,
        ViewModelModule.class,
        ItemViewModelModule.class,
        RemoteDataAccessModule.class,
        LocalDataAccessModule.class,
        HttpModule.class,
        BusinessModule.class,
        TaskModule.class})
public interface IContainer {
    void inject(ConnectionSettingsFragment fragment);
    void inject(ProductCatalogFragment fragment);
    void inject(ShoppingCartFragment fragment);

}
