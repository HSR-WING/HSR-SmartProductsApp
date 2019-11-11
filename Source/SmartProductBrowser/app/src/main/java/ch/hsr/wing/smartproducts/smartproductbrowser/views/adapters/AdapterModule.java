package ch.hsr.wing.smartproducts.smartproductbrowser.views.adapters;

import javax.inject.Provider;

import ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels.ProductViewModel;
import dagger.Module;
import dagger.Provides;

@Module
public class AdapterModule {

    @Provides
    public ProductsAdapter createAdapter(Provider<ProductViewModel> vmFactory){
        return new ProductsAdapter(vmFactory);
    }
}
