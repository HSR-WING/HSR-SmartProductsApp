package ch.hsr.wing.smartproducts.smartproductbrowser.util;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageUtil {
    public static byte[] toByteArray(Bitmap image) throws IOException {
        try(ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            image.compress(Bitmap.CompressFormat.PNG, 100, stream);
            return stream.toByteArray();
        }
    }
}
