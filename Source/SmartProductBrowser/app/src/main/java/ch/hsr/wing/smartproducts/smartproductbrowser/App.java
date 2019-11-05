package ch.hsr.wing.smartproducts.smartproductbrowser;

import android.content.SharedPreferences;

import ch.hsr.wing.smartproducts.smartproductbrowser.di.DI;
import ch.hsr.wing.smartproducts.smartproductbrowser.di.IContainer;

public class App extends android.app.Application {

    private IContainer _container;

    @Override
    public void onCreate(){
        super.onCreate();
        this._container = DI.setup(this);
    }

    public IContainer getContainer(){
        return this._container;
    }

    private static final String SETTINGS_NAME = "HSR.SmartShoppingCart.Settings";

    public SharedPreferences getSettings(){
        return this.getSharedPreferences(SETTINGS_NAME, MODE_PRIVATE);
    }
}
