package ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.InvalidObjectException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.entities.ContentResponse;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.entities.ProductDto;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.entities.ProductInfoDto;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.entities.ResponseTypes;
import ch.hsr.wing.smartproducts.smartproductbrowser.util.settings.IConnectionSettings;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

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
                return this.toResponseType(response.code());
            }
        } catch (Exception ex){
            return ResponseTypes.UNREACHABLE;
        }
    }

    private static final String ALL_PRODUCTS = "products";
    @Override
    public ContentResponse<List<ProductInfoDto>> getAllProducts() {
        try{
            HttpUrl.Builder url = this.getApi().addPathSegment(ALL_PRODUCTS);
            Request request = new Request.Builder().url(url.toString()).build();
            try(Response response = this._client.newCall(request).execute()){
                if(!response.isSuccessful()){
                    return this.noSuccess(response);
                }
                List<ProductInfoDto> dtos = this.parseJsonToProductInfo(response.body());
                return new ContentResponse<>(ResponseTypes.OK, dtos);
            }
        } catch (Exception ex){
            return new ContentResponse<>(ResponseTypes.UNREACHABLE);
        }
    }

    private List<ProductInfoDto> parseJsonToProductInfo(ResponseBody body){
        if(!body.contentType().equals(MediaType.get("application/json"))){
            throw new JsonParseException("Content is not JSON.");
        }
        try {
            ProductInfoDto[] content = new Gson().fromJson(body.string(), ProductInfoDto[].class);
            return new LinkedList<>(Arrays.asList(content));
        } catch (Exception ex) {
            throw new JsonParseException("Cannot parse ProductInfo.", ex);
        }
    }

    private static final String PRODUCT_BY_ID = "products";
    @Override
    public ContentResponse<ProductDto> getDetailsOfProductById(UUID productId) {
        return null;
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
