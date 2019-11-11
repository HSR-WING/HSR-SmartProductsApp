package ch.hsr.wing.smartproducts.smartproductbrowser;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

public interface IApp {
    Context getAppContext();
    SharedPreferences getSettings();
    Bitmap getImage(int resource);
}
