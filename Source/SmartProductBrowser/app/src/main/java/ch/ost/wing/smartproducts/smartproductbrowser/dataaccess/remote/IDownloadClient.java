package ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.remote;

import android.graphics.Bitmap;

import ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities.ContentResponse;

public interface IDownloadClient {
    ContentResponse<Bitmap> getImageFrom(String url);
}
