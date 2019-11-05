package ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote;

import org.junit.Test;

import ch.hsr.wing.smartproducts.smartproductbrowser.util.settings.IConnectionSettings;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DataApiClientTest {

    @Test
    public void test_DataApiClient_Ping() throws Exception{
        MockWebServer server = new MockWebServer();

        MockResponse response = new MockResponse().setResponseCode(200);
        server.enqueue(response);

        server.start();

        IConnectionSettings settings = mock(IConnectionSettings.class);

        when(settings.getDataEndpoint()).thenReturn(server.url("/").toString());

        DataApiClient client = new DataApiClient(settings, new OkHttpClient());

        boolean success = client.ping();

        assertTrue(success);
    }

    @Test
    public void test_DataApiClient_Ping_NotSuccessful() throws Exception{
        MockWebServer server = new MockWebServer();

        MockResponse response = new MockResponse().setResponseCode(500);
        server.enqueue(response);

        server.start();

        IConnectionSettings settings = mock(IConnectionSettings.class);

        when(settings.getDataEndpoint()).thenReturn(server.url("/").toString());

        DataApiClient client = new DataApiClient(settings, new OkHttpClient());

        boolean success = client.ping();

        assertFalse(success);
    }
}
