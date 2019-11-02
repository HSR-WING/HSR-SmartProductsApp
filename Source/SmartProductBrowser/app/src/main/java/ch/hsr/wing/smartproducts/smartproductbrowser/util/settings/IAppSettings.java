package ch.hsr.wing.smartproducts.smartproductbrowser.util.settings;

public interface IAppSettings {
    String getString(String key);
    void setString(String key, String value);
}
