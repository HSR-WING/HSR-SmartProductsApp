package ch.hsr.wing.smartproducts.smartproductbrowser.util.settings;

import android.content.SharedPreferences;

public class AppSettings implements IAppSettings {
    private final SharedPreferences _settings;

    public AppSettings(SharedPreferences settings){
        this._settings = settings;
    }

    @Override
    public String getString(String key) {
        return this._settings.getString(key, "");
    }

    @Override
    public void setString(String key, String value) {
        SharedPreferences.Editor editor = this._settings.edit();
        editor.putString(key, value);
        editor.apply();
    }
}
