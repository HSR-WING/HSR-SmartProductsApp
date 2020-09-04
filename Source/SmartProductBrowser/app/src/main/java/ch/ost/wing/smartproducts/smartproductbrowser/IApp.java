package ch.ost.wing.smartproducts.smartproductbrowser;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

public interface IApp {
    Context getAppContext();
    String getString(int resource);
    SharedPreferences getSettings();
    Bitmap getImageFromDrawable(int resource);
}
