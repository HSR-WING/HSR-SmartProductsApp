package ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote;

import javax.inject.Inject;

import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.entities.DataDto;
import ch.hsr.wing.smartproducts.smartproductbrowser.util.settings.IConnectionSettings;
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
    public boolean ping(){
        try {
            HttpUrl.Builder url = this.getApi().addPathSegment(PING_URL);
            Request request = new Request.Builder().url(url.toString()).build();
            try (Response response = this._client.newCall(request).execute()) {
                return response.isSuccessful();
            }
        } catch (Exception ex){
            return false;
        }
    }

    private static final String DATA_LATEST_URL = "/collection/{coll}/data/latest";
    @Override
    public DataDto getLatest() {

        return null;
    }
}
