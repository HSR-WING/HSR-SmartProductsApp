package ch.ost.wing.smartproducts.smartproductbrowser.di;

import javax.inject.Singleton;

import ch.ost.wing.smartproducts.smartproductbrowser.AppModule;
import ch.ost.wing.smartproducts.smartproductbrowser.businesslogic.BusinessModule;
import ch.ost.wing.smartproducts.smartproductbrowser.businesslogic.TimerModule;
import ch.ost.wing.smartproducts.smartproductbrowser.businesslogic.tasks.TaskModule;
import ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.local.LocalDataAccessModule;
import ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.remote.RemoteDataAccessModule;
import ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.remote.HttpModule;
import ch.ost.wing.smartproducts.smartproductbrowser.util.settings.SettingsModule;
import ch.ost.wing.smartproducts.smartproductbrowser.viewmodels.ItemViewModelModule;
import ch.ost.wing.smartproducts.smartproductbrowser.viewmodels.ViewModelModule;
import ch.ost.wing.smartproducts.smartproductbrowser.views.MainActivity;
import ch.ost.wing.smartproducts.smartproductbrowser.views.ProductCatalogFragment;
import ch.ost.wing.smartproducts.smartproductbrowser.views.ShoppingCartFragment;
import ch.ost.wing.smartproducts.smartproductbrowser.views.adapters.AdapterModule;
import ch.ost.wing.smartproducts.smartproductbrowser.views.settings.ConnectionSettingsFragment;
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
        TimerModule.class,
        TaskModule.class})
public interface IContainer {
    void inject(MainActivity activity);
    void inject(ConnectionSettingsFragment fragment);
    void inject(ProductCatalogFragment fragment);
    void inject(ShoppingCartFragment fragment);
}
