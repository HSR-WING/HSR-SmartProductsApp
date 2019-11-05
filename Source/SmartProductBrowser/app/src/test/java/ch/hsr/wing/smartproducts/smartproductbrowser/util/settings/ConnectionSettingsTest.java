package ch.hsr.wing.smartproducts.smartproductbrowser.util.settings;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ConnectionSettingsTest {

    @Test
    public void test_GetDataEndpoint(){
        IAppSettings appSettings = mock(IAppSettings.class);
        when(appSettings.getString("DataEndpoint")).thenReturn("Data_Endpoint");

        ConnectionSettings settings = new ConnectionSettings(appSettings);

        assertEquals("Data_Endpoint", settings.getDataEndpoint());
    }

    @Test
    public void test_SetDataEndpoint(){
        IAppSettings appSettings = mock(IAppSettings.class);

        ConnectionSettings settings = new ConnectionSettings(appSettings);

        settings.setDataEndpoint("Data_Endpoint");

        verify(appSettings).setString("DataEndpoint", "Data_Endpoint");
    }

    @Test
    public void test_GetProductsEndpoint(){
        IAppSettings appSettings = mock(IAppSettings.class);
        when(appSettings.getString("ProductsEndpoint")).thenReturn("Products_Endpoint");

        ConnectionSettings settings = new ConnectionSettings(appSettings);

        assertEquals("Products_Endpoint", settings.getProductsEndpoint());
    }

    @Test
    public void test_SetProductsEndpoint(){
        IAppSettings appSettings = mock(IAppSettings.class);

        ConnectionSettings settings = new ConnectionSettings(appSettings);

        settings.setProductsEndpoint("Products_Endpoint");

        verify(appSettings).setString("ProductsEndpoint", "Products_Endpoint");
    }
}
