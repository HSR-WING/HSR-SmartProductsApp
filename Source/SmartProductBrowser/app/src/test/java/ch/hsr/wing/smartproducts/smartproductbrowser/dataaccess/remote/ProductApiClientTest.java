package ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote;

import org.junit.Test;

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
}
