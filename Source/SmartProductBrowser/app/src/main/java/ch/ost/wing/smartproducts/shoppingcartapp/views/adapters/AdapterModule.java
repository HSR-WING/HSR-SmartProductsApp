package ch.ost.wing.smartproducts.shoppingcartapp.views.adapters;

import javax.inject.Provider;

import ch.ost.wing.smartproducts.shoppingcartapp.IApp;
import ch.ost.wing.smartproducts.shoppingcartapp.viewmodels.ProductViewModel;
import dagger.Module;
import dagger.Provides;

@Module
public class AdapterModule {

    @Provides
    public ProductsAdapter createAdapter(Provider<ProductViewModel> vmFactory, IApp app){
        return new ProductsAdapter(vmFactory, app);
    }
}
