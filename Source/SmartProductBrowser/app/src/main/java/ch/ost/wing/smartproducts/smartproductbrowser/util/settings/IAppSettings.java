package ch.ost.wing.smartproducts.smartproductbrowser.util.settings;

public interface IAppSettings {
    String getString(String key);
    void setString(String key, String value);

    int getInt(String key);
    void setInt(String key, int value);
}
