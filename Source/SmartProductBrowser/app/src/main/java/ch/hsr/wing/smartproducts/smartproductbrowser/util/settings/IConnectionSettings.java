package ch.hsr.wing.smartproducts.smartproductbrowser.util.settings;

public interface IConnectionSettings {
    String getDataEndpoint();
    void setDataEndpoint(String endpoint);

    String getProductsEndpoint();
    void setProductsEndpoint(String endpoint);
}
