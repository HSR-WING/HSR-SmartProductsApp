package ch.hsr.wing.smartproducts.smartproductbrowser.util.settings;

import android.content.SharedPreferences;

public interface IAppSettings {
    SharedPreferences getSettings();
    String getString(String key);
    void setString(String key, String value);
}
