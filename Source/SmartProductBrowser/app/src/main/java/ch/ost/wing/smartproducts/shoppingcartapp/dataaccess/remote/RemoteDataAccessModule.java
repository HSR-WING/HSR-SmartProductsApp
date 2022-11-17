package ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RemoteDataAccessModule {
    @Binds
    public abstract IDataApiClient getDataApiClient(DataApiClient client);

    @Binds
    public abstract IProductApiClient getProductApiClient(ProductApiClient client);

    @Binds
    public abstract IDownloadClient getDownloadClient(DownloadClient client);
}
