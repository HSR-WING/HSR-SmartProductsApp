package ch.ost.wing.smartproducts.shoppingcartapp.viewmodels;

import ch.ost.wing.smartproducts.shoppingcartapp.IApp;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.local.IProductRepository;
import dagger.Module;
import dagger.Provides;

@Module
public class ItemViewModelModule {
    @Provides
    public ProductViewModel getProductViewModel(IProductRepository repo, IApp app){
        return new ProductViewModel(repo, app);
    }
}
