package ch.ost.wing.smartproducts.shoppingcartapp.viewmodels;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ch.ost.wing.smartproducts.shoppingcartapp.di.ViewModelKey;
import ch.ost.wing.smartproducts.shoppingcartapp.di.ViewModelProviderFactory;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProductCatalogViewModel.class)
    public abstract ViewModel getProductCatalogViewModel(ProductCatalogViewModel vm);

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel.class)
    public abstract ViewModel getSettingsViewModel(SettingsViewModel vm);

    @Binds
    @IntoMap
    @ViewModelKey(ShoppingCartViewModel.class)
    public abstract ViewModel getShoppingCartViewModel(ShoppingCartViewModel vm);

    @Binds
    public abstract ViewModelProvider.Factory getSettingsViewModelFactory(ViewModelProviderFactory factory);



}
