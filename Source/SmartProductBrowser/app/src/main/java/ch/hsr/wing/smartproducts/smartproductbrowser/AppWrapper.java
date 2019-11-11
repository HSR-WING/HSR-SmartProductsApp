package ch.hsr.wing.smartproducts.smartproductbrowser;

import android.content.Context;
import android.content.SharedPreferences;

public class AppWrapper implements IApp {

    private final App _app;

    public AppWrapper(App app){
        this._app = app;
    }

    @Override
    public Context getAppContext() {
        return this._app.getApplicationContext();
    }

    @Override
    public SharedPreferences getSettings() {
        return this._app.getSettings();
    }
}
