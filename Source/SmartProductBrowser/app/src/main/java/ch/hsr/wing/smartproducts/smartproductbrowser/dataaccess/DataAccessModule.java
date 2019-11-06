package ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess;

import org.intellij.lang.annotations.PrintFormat;

import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.DataApiClient;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.IDataApiClient;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.IProductApiClient;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.ProductApiClient;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class DataAccessModule {
    @Binds
    public abstract IDataApiClient getDataApiClient(DataApiClient client);

    @Binds
    public abstract IProductApiClient getProductApiClient(ProductApiClient client);
}
