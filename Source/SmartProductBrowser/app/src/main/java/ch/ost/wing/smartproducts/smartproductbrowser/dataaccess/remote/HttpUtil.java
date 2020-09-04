package ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities.ContentResponse;
import ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities.ResponseTypes;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;

class HttpUtil {

    static boolean isJson(ResponseBody body){
        MediaType expectedType = MediaType.get("application/json");
        MediaType actualType = body.contentType();
        return actualType.type().equals(expectedType.type())
                && actualType.subtype().equals(expectedType.subtype());
    }

    static <T> ContentResponse<T> noSuccess(Response response){
        return new ContentResponse<>(toResponseType(response.code()));
    }

    static ResponseTypes toResponseType(int statusCode){
        if(statusCode >= 500){
            return ResponseTypes.SERVER_ERROR;
        }
        return ResponseTypes.BAD_REQUEST;
    }

    static <T> T parseJsonTo(Class<T> c, ResponseBody body){
        if(!HttpUtil.isJson(body)){
            throw new JsonParseException("Content is not JSON.");
        }
        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
            T content = gson.fromJson(body.string(), c);
            return content;
        } catch (Exception ex) {
            throw new JsonParseException("Cannot parse content.", ex);
        }
    }
}
