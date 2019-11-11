package ch.hsr.wing.smartproducts.smartproductbrowser;

import android.content.Context;
import android.content.SharedPreferences;

public interface IApp {
    Context getAppContext();
    SharedPreferences getSettings();
}
