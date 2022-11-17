package ch.ost.wing.smartproducts.shoppingcartapp.util.settings;

import javax.inject.Inject;

public class ConnectionSettings implements IConnectionSettings {

    private final IAppSettings _settings;

    @Inject
    public ConnectionSettings(IAppSettings settings){
        this._settings = settings;
    }

    private static final String DATA_ENDPOINT_KEY = "DataEndpoint";
    @Override
    public String getDataEndpoint() {
        return this._settings.getString(DATA_ENDPOINT_KEY);
    }

    @Override
    public void setDataEndpoint(String endpoint) {
        this._settings.setString(DATA_ENDPOINT_KEY, endpoint);
    }

    private static final String DATA_COLLECTION_KEY = "DataCollection";
    @Override
    public String getDataCollection() {
        return this._settings.getString(DATA_COLLECTION_KEY);
    }

    @Override
    public void setDataCollection(String collection) {
        this._settings.setString(DATA_COLLECTION_KEY, collection);
    }

    private static final String PRODUCTS_ENDPOINT_KEY = "ProductsEndpoint";
    @Override
    public String getProductsEndpoint() {
        return this._settings.getString(PRODUCTS_ENDPOINT_KEY);
    }

    @Override
    public void setProductsEndpoint(String endpoint) {
        this._settings.setString(PRODUCTS_ENDPOINT_KEY, endpoint);
    }
}
