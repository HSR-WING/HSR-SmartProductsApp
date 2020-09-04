package ch.ost.wing.smartproducts.smartproductbrowser.views.adapters;

import javax.inject.Provider;

import ch.ost.wing.smartproducts.smartproductbrowser.IApp;
import ch.ost.wing.smartproducts.smartproductbrowser.viewmodels.ProductViewModel;
import dagger.Module;
import dagger.Provides;

@Module
public class AdapterModule {

    @Provides
    public ProductsAdapter createAdapter(Provider<ProductViewModel> vmFactory, IApp app){
        return new ProductsAdapter(vmFactory, app);
    }
}
