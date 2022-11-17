package ch.ost.wing.smartproducts.shoppingcartapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

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
    public String getString(int resource) {
        return this.getAppContext().getResources().getString(resource);
    }

    @Override
    public SharedPreferences getSettings() {
        return this._app.getSettings();
    }


    @Override
    public Bitmap getImageFromDrawable(int resource) {
        Drawable drawable = this._app.getResources().getDrawable(resource, this._app.getTheme());
        return this.toBitmap(drawable);
    }

    private Bitmap toBitmap(Drawable drawable) {
        if(drawable instanceof BitmapDrawable){
            return ((BitmapDrawable)drawable).getBitmap();
        }

        Bitmap image = Bitmap.createBitmap(300,300, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        drawable.setBounds(0,0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return image;
    }
}
