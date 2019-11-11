package ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.tasks;

import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local.IProductRepository;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.IDownloadClient;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.IProductApiClient;
import dagger.Module;
import dagger.Provides;

@Module
public class TaskModule {

    @Provides
    public LoadProductsTask createLoadProductsTask(IProductRepository repo){
        return new LoadProductsTask(repo);
    }

    @Provides
    public RefreshProductsTask createRefreshProductsTask(IProductApiClient client, IProductRepository repo, IDownloadClient download){
        return new RefreshProductsTask(client, repo, download);
    }
}
