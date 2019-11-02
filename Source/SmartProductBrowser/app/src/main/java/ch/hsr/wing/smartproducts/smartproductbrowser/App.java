package ch.hsr.wing.smartproducts.smartproductbrowser;

import android.content.SharedPreferences;

import ch.hsr.wing.smartproducts.smartproductbrowser.di.DI;

public class App extends android.app.Application {
    @Override
    public void onCreate(){
        super.onCreate();
        DI.setup(this);
    }

    private static final String SETTINGS_NAME = "HSR.SmartShoppingCart.Settings";

    public SharedPreferences getSettings(){
        return this.getSharedPreferences(SETTINGS_NAME, MODE_PRIVATE);
    }
}
