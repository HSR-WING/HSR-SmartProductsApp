package ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import javax.inject.Inject;

import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities.ContentResponse;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities.ResponseTypes;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadClient implements IDownloadClient {

    private final OkHttpClient _client;

    @Inject
    public DownloadClient(OkHttpClient client){
        this._client = client;
    }

    @Override
    public ContentResponse<Bitmap> getImageFrom(String url) {
        try {
            Request request = new Request.Builder().url(url).build();
            try(Response response = this._client.newCall(request).execute()){
                if(!response.isSuccessful()){
                    return this.noSuccess(response);
                }
                Bitmap image = BitmapFactory.decodeStream(response.body().byteStream());
                return new ContentResponse<>(ResponseTypes.OK, image);
            }
        } catch (Throwable ex){
            ex.printStackTrace();
            return new ContentResponse<>(ResponseTypes.UNREACHABLE);
        }
    }

    private <T> ContentResponse<T> noSuccess(Response response){
        return new ContentResponse<>(this.toResponseType(response.code()));
    }

    private ResponseTypes toResponseType(int statusCode){
        if(statusCode >= 500){
            return ResponseTypes.SERVER_ERROR;
        }
        return ResponseTypes.BAD_REQUEST;
    }
}
