package ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote;

import android.graphics.Bitmap;

import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities.ContentResponse;

public interface IDownloadClient {
    ContentResponse<Bitmap> getImageFrom(String url);
}
