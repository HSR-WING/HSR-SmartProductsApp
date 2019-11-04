package ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels;

import androidx.lifecycle.ViewModelProvider;

import ch.hsr.wing.smartproducts.smartproductbrowser.di.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class ShoppingCartViewModelModule {

    @Provides
    public ShoppingCartViewModel getShoppingCartViewModel(){
        return new ShoppingCartViewModel();
    }

    @Provides
    public ViewModelProvider.Factory getShoppingCartViewModelFactory(ShoppingCartViewModel vm){
        return new ViewModelProviderFactory<>(vm);
    }
}
