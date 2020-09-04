package ch.ost.wing.smartproducts.smartproductbrowser.util.settings;

import android.content.SharedPreferences;

import javax.inject.Inject;

import ch.ost.wing.smartproducts.smartproductbrowser.IApp;

public class AppSettings implements IAppSettings {
    private final IApp _app;

    @Inject
    public AppSettings(IApp app) {
        this._app = app;
    }

    private SharedPreferences getSettings(){
        return this._app.getSettings();
    }

    @Override
    public String getString(String key) {
        return this.getSettings().getString(key, "");
    }

    @Override
    public void setString(String key, String value) {
        SharedPreferences.Editor editor = this.getSettings().edit();
        editor.putString(key, value);
        editor.apply();
    }

    @Override
    public int getInt(String key) {
        return this.getSettings().getInt(key, 0);
    }

    @Override
    public void setInt(String key, int value) {
        SharedPreferences.Editor editor = this.getSettings().edit();
        editor.putInt(key, value);
        editor.apply();
    }
}
