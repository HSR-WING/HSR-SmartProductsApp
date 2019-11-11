package ch.hsr.wing.smartproducts.smartproductbrowser.views.adapters;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {
    @BindingAdapter("bind.imageBitmap")
    public static void bindImage(ImageView iv, Bitmap image){
        iv.setImageBitmap(image);
    }
}
