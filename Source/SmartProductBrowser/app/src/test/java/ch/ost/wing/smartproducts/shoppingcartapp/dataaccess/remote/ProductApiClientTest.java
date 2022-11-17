package ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.junit.Test;

import java.util.List;
import java.util.UUID;

import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities.ContentResponse;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities.ProductDto;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities.ProductInfoDto;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities.ResponseTypes;
import ch.ost.wing.smartproducts.shoppingcartapp.util.settings.IConnectionSettings;
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

    @Test
    public void test_ProductApiClient_Products_Content_MultipleObjects() throws Exception{
        MockWebServer server = new MockWebServer();

        UUID pid1 = UUID.randomUUID();
        JsonObject jsonProduct1 = new JsonObject();
        jsonProduct1.addProperty("Id", pid1.toString());
        jsonProduct1.addProperty("Name", "Coffee");
        jsonProduct1.addProperty("ImageUrl", "http://coff.ee");

        UUID pid2 = UUID.randomUUID();
        JsonObject jsonProduct2 = new JsonObject();
        jsonProduct2.addProperty("Id", pid2.toString());
        jsonProduct2.addProperty("Name", "Chips");
        jsonProduct2.addProperty("ImageUrl", "http://chi.ps");

        JsonArray jsonProducts = new JsonArray();
        jsonProducts.add(jsonProduct1);
        jsonProducts.add(jsonProduct2);

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
        assertEquals(2, result.getContent().size());

        ProductInfoDto firstProduct = result.getContent().get(0);
        assertEquals(pid1, firstProduct.getId());
        assertEquals("Coffee", firstProduct.getName());
        assertEquals("http://coff.ee", firstProduct.getImageUrl());

        ProductInfoDto secondProduct = result.getContent().get(1);
        assertEquals(pid2, secondProduct.getId());
        assertEquals("Chips", secondProduct.getName());
        assertEquals("http://chi.ps", secondProduct.getImageUrl());
    }

    @Test
    public void test_ProductApiClient_Product_ById_StatusCode() throws Exception{
        MockWebServer server = new MockWebServer();

        UUID pid = UUID.randomUUID();
        JsonObject jsonProduct = new JsonObject();

        String jsonResponse = jsonProduct.toString();
        MockResponse response = new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(jsonResponse);
        server.enqueue(response);

        server.start();

        IConnectionSettings settings = mock(IConnectionSettings.class);
        when(settings.getProductsEndpoint()).thenReturn(server.url("/").toString());

        ProductApiClient client = new ProductApiClient(settings, new OkHttpClient());

        ContentResponse<ProductDto> result = client.getDetailsOfProductById(pid);

        assertEquals(ResponseTypes.OK, result.getResponseType());
    }

    @Test
    public void test_ProductApiClient_Product_ById_Content() throws Exception{
        MockWebServer server = new MockWebServer();

        UUID pid = UUID.randomUUID();
        JsonObject jsonProduct = new JsonObject();
        jsonProduct.addProperty("Id", pid.toString());
        jsonProduct.addProperty("ArticleNumber", "co-0042");
        jsonProduct.addProperty("Name", "Coffee");
        jsonProduct.addProperty("ImageUrl", "http://coff.ee");
        jsonProduct.addProperty("Price", 42.21);
        jsonProduct.addProperty("Weight", 1337.0);
        jsonProduct.addProperty("Retailer", "Migros");

        String jsonResponse = jsonProduct.toString();
        MockResponse response = new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(jsonResponse);
        server.enqueue(response);

        server.start();

        IConnectionSettings settings = mock(IConnectionSettings.class);
        when(settings.getProductsEndpoint()).thenReturn(server.url("/").toString());

        ProductApiClient client = new ProductApiClient(settings, new OkHttpClient());

        ContentResponse<ProductDto> result = client.getDetailsOfProductById(pid);

        assertEquals(ResponseTypes.OK, result.getResponseType());
        assertTrue(result.hasContent());

        ProductDto product = result.getContent();
        assertEquals(pid, product.getId());
        assertEquals("co-0042", product.getArticleNumber());
        assertEquals("Coffee", product.getName());
        assertEquals("http://coff.ee", product.getImageUrl());
        assertEquals(42.21, product.getPrice(), .0001);
        assertEquals(1337.0, product.getWeight(), .0001);
        assertEquals("Migros", product.getRetailer());
    }
}
