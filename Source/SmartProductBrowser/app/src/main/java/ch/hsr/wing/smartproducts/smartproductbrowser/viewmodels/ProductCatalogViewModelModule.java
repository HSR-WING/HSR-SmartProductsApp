package ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels;

import androidx.lifecycle.ViewModelProvider;

import ch.hsr.wing.smartproducts.smartproductbrowser.di.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class ProductCatalogViewModelModule {

    @Provides
    public ProductCatalogViewModel getProductCatalogViewModel(){
        return new ProductCatalogViewModel();
    }

    @Provides
    public ViewModelProvider.Factory getProductCatalogViewModelFactory(ProductCatalogViewModel vm){
        return new ViewModelProviderFactory<>(vm);
    }
}
