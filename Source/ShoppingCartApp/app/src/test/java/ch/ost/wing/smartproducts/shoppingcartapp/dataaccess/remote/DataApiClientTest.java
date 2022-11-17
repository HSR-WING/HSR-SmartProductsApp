package ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote;

import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.UUID;

import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities.ContentResponse;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities.DataDto;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities.ResponseTypes;
import ch.ost.wing.smartproducts.shoppingcartapp.util.settings.IConnectionSettings;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import okhttp3.mockwebserver.SocketPolicy;

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

        ResponseTypes result = client.ping();

        assertEquals(ResponseTypes.OK, result);
    }

    @Test
    public void test_DataApiClient_Ping_NotSuccessful_ServerError() throws Exception{
        MockWebServer server = new MockWebServer();

        MockResponse response = new MockResponse().setResponseCode(500);
        server.enqueue(response);

        server.start();

        IConnectionSettings settings = mock(IConnectionSettings.class);

        when(settings.getDataEndpoint()).thenReturn(server.url("/").toString());

        DataApiClient client = new DataApiClient(settings, new OkHttpClient());

        ResponseTypes result = client.ping();

        assertEquals(ResponseTypes.SERVER_ERROR, result);
    }

    @Test
    public void test_DataApiClient_Ping_NotSuccessful_NoResponse() throws Exception{
        MockWebServer server = new MockWebServer();

        MockResponse response = new MockResponse();
        response.socketPolicy(SocketPolicy.NO_RESPONSE);
        server.enqueue(response);

        server.start();

        IConnectionSettings settings = mock(IConnectionSettings.class);

        when(settings.getDataEndpoint()).thenReturn(server.url("/").toString());

        DataApiClient client = new DataApiClient(settings, new OkHttpClient());

        ResponseTypes result = client.ping();

        assertEquals(ResponseTypes.UNREACHABLE, result);
    }

    @Test
    public void test_DataApiClient_GetLatest_ServerError() throws Exception{
        MockWebServer server = new MockWebServer();

        MockResponse response = new MockResponse().setResponseCode(500);
        server.enqueue(response);

        server.start();

        IConnectionSettings settings = mock(IConnectionSettings.class);

        when(settings.getDataEndpoint()).thenReturn(server.url("/").toString());
        when(settings.getDataCollection()).thenReturn("test");

        DataApiClient client = new DataApiClient(settings, new OkHttpClient());

        ContentResponse<DataDto> result = client.getLatest();

        assertEquals(ResponseTypes.SERVER_ERROR, result.getResponseType());
    }

    @Test
    public void test_DataApiClient_GetLatest_BadRequest() throws Exception{
        MockWebServer server = new MockWebServer();

        MockResponse response = new MockResponse().setResponseCode(400);
        server.enqueue(response);

        server.start();

        IConnectionSettings settings = mock(IConnectionSettings.class);

        when(settings.getDataEndpoint()).thenReturn(server.url("/").toString());
        when(settings.getDataCollection()).thenReturn("test");

        DataApiClient client = new DataApiClient(settings, new OkHttpClient());

        ContentResponse<DataDto> result = client.getLatest();

        assertEquals(ResponseTypes.BAD_REQUEST, result.getResponseType());
    }

    @Test
    public void test_DataApiClient_GetLatests_NoResponse() throws Exception{
        MockWebServer server = new MockWebServer();

        MockResponse response = new MockResponse();
        response.socketPolicy(SocketPolicy.NO_RESPONSE);
        server.enqueue(response);

        server.start();

        IConnectionSettings settings = mock(IConnectionSettings.class);

        when(settings.getDataEndpoint()).thenReturn(server.url("/").toString());
        when(settings.getDataCollection()).thenReturn("test");

        DataApiClient client = new DataApiClient(settings, new OkHttpClient());

        ContentResponse<DataDto> result = client.getLatest();

        assertEquals(ResponseTypes.UNREACHABLE, result.getResponseType());
    }

    private MockResponse getJsonResponse(){
        return new MockResponse().setHeader("Content-Type", "application/json");
    }

    @Test
    public void test_DataApiClient_GetLatest_Url() throws Exception{
        MockWebServer server = new MockWebServer();

        server.setDispatcher(new Dispatcher() {
            @NotNull
            @Override
            public MockResponse dispatch(@NotNull RecordedRequest recordedRequest) throws InterruptedException {
                if(!recordedRequest.getPath().equals("/api/db/collection/test/data/latest")){
                    return getJsonResponse().setResponseCode(400);
                }
                return getJsonResponse().setResponseCode(200);
            }
        });

        server.start();

        IConnectionSettings settings = mock(IConnectionSettings.class);
        when(settings.getDataEndpoint()).thenReturn(server.url("/").toString());
        when(settings.getDataCollection()).thenReturn("test");

        DataApiClient client = new DataApiClient(settings, new OkHttpClient());

        ContentResponse<DataDto> result = client.getLatest();

        assertEquals(ResponseTypes.OK, result.getResponseType());
    }

    @Test
    public void test_DataApiClient_GetLatest_Url_Wrong() throws Exception{
        MockWebServer server = new MockWebServer();

        server.setDispatcher(new Dispatcher() {
            @NotNull
            @Override
            public MockResponse dispatch(@NotNull RecordedRequest recordedRequest) throws InterruptedException {
                if(!recordedRequest.getPath().equals("/api/db/collection/test/data/latest")){
                    return getJsonResponse().setResponseCode(404);
                }
                return getJsonResponse().setResponseCode(200);
            }
        });

        server.start();

        IConnectionSettings settings = mock(IConnectionSettings.class);
        when(settings.getDataEndpoint()).thenReturn(server.url("/").toString());
        when(settings.getDataCollection()).thenReturn("other");

        DataApiClient client = new DataApiClient(settings, new OkHttpClient());

        ContentResponse<DataDto> result = client.getLatest();

        assertEquals(ResponseTypes.BAD_REQUEST, result.getResponseType());
    }

    @Test
    public void test_DataApiClient_GetLatest_Content_NotJson() throws Exception{
        MockWebServer server = new MockWebServer();

        MockResponse response = new MockResponse().setResponseCode(200).setHeader("Content-Type", "application/xml");
        server.enqueue(response);

        server.start();

        IConnectionSettings settings = mock(IConnectionSettings.class);

        when(settings.getDataEndpoint()).thenReturn(server.url("/").toString());
        when(settings.getDataCollection()).thenReturn("test");

        DataApiClient client = new DataApiClient(settings, new OkHttpClient());

        ContentResponse<DataDto> result = client.getLatest();

        assertEquals(ResponseTypes.BAD_RESPONSE, result.getResponseType());
    }

    @Test
    public void test_DataApiClient_GetLatest_Content_Json_Status() throws Exception{
        MockWebServer server = new MockWebServer();


        UUID pid = UUID.randomUUID();
        JsonObject jsonProduct = new JsonObject();
        jsonProduct.addProperty("Id", pid.toString());
        jsonProduct.addProperty("Timestamp", "2019-10-18T07:32:40.948Z");
        jsonProduct.add("Data", new JsonObject());

        MockResponse response = new MockResponse().setResponseCode(200).setHeader("Content-Type", "application/json");
        server.enqueue(response);

        server.start();

        IConnectionSettings settings = mock(IConnectionSettings.class);

        when(settings.getDataEndpoint()).thenReturn(server.url("/").toString());
        when(settings.getDataCollection()).thenReturn("test");

        DataApiClient client = new DataApiClient(settings, new OkHttpClient());

        ContentResponse<DataDto> result = client.getLatest();

        assertEquals(ResponseTypes.OK, result.getResponseType());
    }

    @Test
    public void test_DataApiClient_GetLatest_Content_Json() throws Exception{
        MockWebServer server = new MockWebServer();


        UUID id = UUID.randomUUID();
        JsonObject jsonData = new JsonObject();
        jsonData.addProperty("Id", id.toString());
        jsonData.addProperty("Timestamp", "2019-10-18T07:32:40.948Z");
        jsonData.add("Data", new JsonObject());

        MockResponse response = new MockResponse().setResponseCode(200).setHeader("Content-Type", "application/json").setBody(jsonData.toString());
        server.enqueue(response);

        server.start();

        IConnectionSettings settings = mock(IConnectionSettings.class);

        when(settings.getDataEndpoint()).thenReturn(server.url("/").toString());
        when(settings.getDataCollection()).thenReturn("test");

        DataApiClient client = new DataApiClient(settings, new OkHttpClient());

        ContentResponse<DataDto> result = client.getLatest();

        assertTrue(result.hasContent());
        DataDto data = result.getContent();
        assertEquals(id, data.getId());
        assertEquals(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2019-10-18T07:32:40.948Z"), data.getTimestamp());
        assertNotNull(data.getData());
    }

    @Test
    public void test_DataApiClient_GetLatest_Content_Json_Content() throws Exception{
        MockWebServer server = new MockWebServer();


        UUID id = UUID.randomUUID();
        JsonObject jsonData = new JsonObject();
        JsonObject jsonValue = new JsonObject();
        jsonValue.addProperty("value", 42);
        jsonData.add("Data", jsonValue);

        MockResponse response = new MockResponse().setResponseCode(200).setHeader("Content-Type", "application/json").setBody(jsonData.toString());
        server.enqueue(response);

        server.start();

        IConnectionSettings settings = mock(IConnectionSettings.class);

        when(settings.getDataEndpoint()).thenReturn(server.url("/").toString());
        when(settings.getDataCollection()).thenReturn("test");

        DataApiClient client = new DataApiClient(settings, new OkHttpClient());

        ContentResponse<DataDto> result = client.getLatest();

        assertTrue(result.hasContent());
        DataDto data = result.getContent();
        assertNotNull(data.getData());
        JsonObject dataValue = data.getData();
        assertNotNull(dataValue);
        assertEquals(42, dataValue.get("value").getAsInt());
    }
}
