package ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote;

import androidx.databinding.BindingAdapter;

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
