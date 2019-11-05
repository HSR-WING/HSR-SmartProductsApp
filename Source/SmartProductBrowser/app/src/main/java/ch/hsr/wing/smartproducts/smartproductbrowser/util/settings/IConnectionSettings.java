package ch.hsr.wing.smartproducts.smartproductbrowser.util.settings;

public interface IConnectionSettings {
    String getDataEndpoint();
    void setDataEndpoint(String endpoint);

    String getDataCollection();
    void setDataCollection(String collection);

    String getProductsEndpoint();
    void setProductsEndpoint(String endpoint);
}
