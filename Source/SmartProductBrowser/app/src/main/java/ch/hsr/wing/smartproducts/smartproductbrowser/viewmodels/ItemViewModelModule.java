package ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels;

import ch.hsr.wing.smartproducts.smartproductbrowser.IApp;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local.IProductRepository;
import dagger.Module;
import dagger.Provides;

@Module
public class ItemViewModelModule {
    @Provides
    public ProductViewModel getProductViewModel(IProductRepository repo, IApp app){
        return new ProductViewModel(repo, app);
    }
}
