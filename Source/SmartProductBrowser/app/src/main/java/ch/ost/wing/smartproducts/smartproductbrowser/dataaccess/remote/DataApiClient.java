package ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.remote;

import com.google.gson.JsonParseException;

import javax.inject.Inject;

import ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities.ContentResponse;
import ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities.DataDto;
import ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities.ResponseTypes;
import ch.ost.wing.smartproducts.smartproductbrowser.util.settings.IConnectionSettings;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataApiClient implements IDataApiClient {

    private final IConnectionSettings _settings;
    private final OkHttpClient _client;

    @Inject
    public DataApiClient(IConnectionSettings settings, OkHttpClient client){
        this._settings = settings;
        this._client = client;
    }

    private static final String API_URL = "api";

    private HttpUrl.Builder getApi(){
        return HttpUrl.parse(this._settings.getDataEndpoint()).newBuilder().addPathSegment(API_URL);
    }

    private static final String PING_URL = "ping";
    @Override
    public ResponseTypes ping(){
        try {
            HttpUrl.Builder url = this.getApi().addPathSegment(PING_URL);
            Request request = new Request.Builder().url(url.toString()).build();
            try (Response response = this._client.newCall(request).execute()) {
                if(response.isSuccessful()){
                    return ResponseTypes.OK;
                }
                return HttpUtil.toResponseType(response.code());
            }
        } catch (Exception ex){
            return ResponseTypes.UNREACHABLE;
        }
    }

    private static final String DATA_COLLECTION_PATH = "db/collection";
    private static final String DATA_LATEST_PATH = "data/latest";
    @Override
    public ContentResponse<DataDto> getLatest() {
        try {
            String container = this._settings.getDataCollection();
            HttpUrl.Builder url = this.getApi().addPathSegments(DATA_COLLECTION_PATH)
                    .addPathSegment(container).addPathSegments(DATA_LATEST_PATH);
            Request request = new Request.Builder().url(url.toString()).build();
            try(Response response = this._client.newCall(request).execute()){
                if(!response.isSuccessful()){
                    return HttpUtil.noSuccess(response);
                }
                DataDto data = HttpUtil.parseJsonTo(DataDto.class, response.body());
                return new ContentResponse<>(ResponseTypes.OK, data);
            }
        }
        catch (JsonParseException ex){
            return new ContentResponse<>(ResponseTypes.BAD_RESPONSE);
        } catch (Exception ex){
            return new ContentResponse<>(ResponseTypes.UNREACHABLE);
        }
    }
}
