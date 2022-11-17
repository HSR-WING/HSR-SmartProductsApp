package ch.ost.wing.smartproducts.shoppingcartapp.views.settings;

import android.content.SharedPreferences;
import android.content.res.Resources;

public interface ISettingsUICallback {
    Resources getResources();
    SharedPreferences getPreferences();
    void refresh(String key);
    void refreshSummary(String key);
}
