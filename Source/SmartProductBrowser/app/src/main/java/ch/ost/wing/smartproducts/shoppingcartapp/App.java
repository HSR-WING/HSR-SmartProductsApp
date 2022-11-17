package ch.ost.wing.smartproducts.shoppingcartapp;

import android.content.SharedPreferences;

import ch.ost.wing.smartproducts.shoppingcartapp.di.DI;
import ch.ost.wing.smartproducts.shoppingcartapp.di.IContainer;

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

    private static final String SETTINGS_NAME = "OST.SmartShoppingCart.Settings";

    public SharedPreferences getSettings(){
        return this.getSharedPreferences(SETTINGS_NAME, MODE_PRIVATE);
    }
}
