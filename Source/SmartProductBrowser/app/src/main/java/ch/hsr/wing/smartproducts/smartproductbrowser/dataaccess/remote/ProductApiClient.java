package ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.entities.ProductDto;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.entities.ProductInfoDto;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.entities.ResponseTypes;
import ch.hsr.wing.smartproducts.smartproductbrowser.util.settings.IConnectionSettings;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ProductApiClient implements IProductApiClient {

    private final IConnectionSettings _settings;
    private final OkHttpClient _client;

    @Inject
    public ProductApiClient(IConnectionSettings settings, OkHttpClient client){
        this._settings = settings;
        this._client = client;
    }

    private static final String API_URL = "api";

    private HttpUrl.Builder getApi(){
        return HttpUrl.parse(this._settings.getProductsEndpoint()).newBuilder().addPathSegment(API_URL);
    }

    private static final String PING_URL = "ping";
    @Override
    public ResponseTypes ping() {
        try {
            HttpUrl.Builder url = this.getApi().addPathSegment(PING_URL);
            Request request = new Request.Builder().url(url.toString()).build();
            try (Response response = this._client.newCall(request).execute()) {
                if(response.isSuccessful()){
                    return ResponseTypes.OK;
                }
                if(response.code() >= 500){
                    return ResponseTypes.SERVER_ERROR;
                }
                return ResponseTypes.BAD_REQUEST;
            }
        } catch (Exception ex){
            return ResponseTypes.UNREACHABLE;
        }
    }

    
    @Override
    public List<ProductInfoDto> getAllProducts() {
        return null;
    }

    @Override
    public ProductDto getDetailsOfProductById(UUID productId) {
        return null;
    }


}
