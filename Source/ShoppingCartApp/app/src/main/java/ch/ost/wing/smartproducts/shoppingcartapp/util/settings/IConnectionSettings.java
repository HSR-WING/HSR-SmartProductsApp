package ch.ost.wing.smartproducts.shoppingcartapp.util.settings;

public interface IConnectionSettings {
    String getDataEndpoint();
    void setDataEndpoint(String endpoint);

    String getDataCollection();
    void setDataCollection(String collection);

    String getProductsEndpoint();
    void setProductsEndpoint(String endpoint);
}
