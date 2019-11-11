package ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.entities.ContentResponse;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.entities.ProductInfoDto;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.entities.ResponseTypes;
import ch.hsr.wing.smartproducts.smartproductbrowser.util.settings.IConnectionSettings;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.SocketPolicy;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProductApiClientTest {

    @Test
    public void test_ProductApiClient_Ping() throws Exception{
        MockWebServer server = new MockWebServer();

        MockResponse response = new MockResponse().setResponseCode(200);
        server.enqueue(response);

        server.start();

        IConnectionSettings settings = mock(IConnectionSettings.class);

        when(settings.getProductsEndpoint()).thenReturn(server.url("/").toString());

        ProductApiClient client = new ProductApiClient(settings, new OkHttpClient());

        ResponseTypes result = client.ping();

        assertEquals(ResponseTypes.OK, result);
    }

    @Test
    public void test_ProductApiClient_Ping_NotSuccessful_ServerError() throws Exception{
        MockWebServer server = new MockWebServer();

        MockResponse response = new MockResponse().setResponseCode(500);
        server.enqueue(response);

        server.start();

        IConnectionSettings settings = mock(IConnectionSettings.class);

        when(settings.getProductsEndpoint()).thenReturn(server.url("/").toString());

        ProductApiClient client = new ProductApiClient(settings, new OkHttpClient());

        ResponseTypes result = client.ping();

        assertEquals(ResponseTypes.SERVER_ERROR, result);
    }

    @Test
    public void test_ProductApiClient_Ping_NotSuccessful_NoResponse() throws Exception{
        MockWebServer server = new MockWebServer();

        MockResponse response = new MockResponse();
        response.socketPolicy(SocketPolicy.NO_RESPONSE);
        server.enqueue(response);

        server.start();

        IConnectionSettings settings = mock(IConnectionSettings.class);

        when(settings.getProductsEndpoint()).thenReturn(server.url("/").toString());

        ProductApiClient client = new ProductApiClient(settings, new OkHttpClient());

        ResponseTypes result = client.ping();

        assertEquals(ResponseTypes.UNREACHABLE, result);
    }

    @Test
    public void test_ProductApiClient_Products() throws Exception{
        MockWebServer server = new MockWebServer();

        MockResponse response = new MockResponse().setResponseCode(200).setHeader("Content-Type", "application/json").setBody("[]");
        server.enqueue(response);

        server.start();

        IConnectionSettings settings = mock(IConnectionSettings.class);

        when(settings.getProductsEndpoint()).thenReturn(server.url("/").toString());

        ProductApiClient client = new ProductApiClient(settings, new OkHttpClient());

        ContentResponse<List<ProductInfoDto>> result = client.getAllProducts();

        assertEquals(ResponseTypes.OK, result.getResponseType());
    }

    @Test
    public void test_ProductApiClient_Products_Content() throws Exception{
        MockWebServer server = new MockWebServer();

        UUID pid = UUID.randomUUID();
        JsonObject jsonProduct = new JsonObject();
        jsonProduct.addProperty("Id", pid.toString());
        jsonProduct.addProperty("Name", "Coffee");
        jsonProduct.addProperty("ImageUrl", "http://coff.ee");

        JsonArray jsonProducts = new JsonArray();
        jsonProducts.add(jsonProduct);

        String jsonResponse = jsonProducts.toString();
        MockResponse response = new MockResponse().setResponseCode(200).setHeader("Content-Type", "application/json").setBody(jsonResponse);
        server.enqueue(response);

        server.start();

        IConnectionSettings settings = mock(IConnectionSettings.class);
        when(settings.getProductsEndpoint()).thenReturn(server.url("/").toString());

        ProductApiClient client = new ProductApiClient(settings, new OkHttpClient());

        ContentResponse<List<ProductInfoDto>> result = client.getAllProducts();

        assertEquals(ResponseTypes.OK, result.getResponseType());
        assertTrue(result.hasContent());
        assertEquals(1, result.getContent().size());

        ProductInfoDto product = result.getContent().get(0);
        assertEquals(pid, product.getId());
        assertEquals("Coffee", product.getName());
        assertEquals("http://coff.ee", product.getImageUrl());
    }
}
