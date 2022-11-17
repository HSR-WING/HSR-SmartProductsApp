package ch.ost.wing.smartproducts.shoppingcartapp.di;

import javax.inject.Singleton;

import ch.ost.wing.smartproducts.shoppingcartapp.AppModule;
import ch.ost.wing.smartproducts.shoppingcartapp.businesslogic.BusinessModule;
import ch.ost.wing.smartproducts.shoppingcartapp.businesslogic.TimerModule;
import ch.ost.wing.smartproducts.shoppingcartapp.businesslogic.tasks.TaskModule;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.local.LocalDataAccessModule;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.RemoteDataAccessModule;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.HttpModule;
import ch.ost.wing.smartproducts.shoppingcartapp.util.settings.SettingsModule;
import ch.ost.wing.smartproducts.shoppingcartapp.viewmodels.ItemViewModelModule;
import ch.ost.wing.smartproducts.shoppingcartapp.viewmodels.ViewModelModule;
import ch.ost.wing.smartproducts.shoppingcartapp.views.MainActivity;
import ch.ost.wing.smartproducts.shoppingcartapp.views.ProductCatalogFragment;
import ch.ost.wing.smartproducts.shoppingcartapp.views.ShoppingCartFragment;
import ch.ost.wing.smartproducts.shoppingcartapp.views.adapters.AdapterModule;
import ch.ost.wing.smartproducts.shoppingcartapp.views.settings.ConnectionSettingsFragment;
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
