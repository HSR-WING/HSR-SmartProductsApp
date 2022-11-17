package ch.ost.wing.smartproducts.shoppingcartapp.util.settings;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ConnectionSettingsTest {

    @Test
    public void test_ConnectionSettings_GetDataEndpoint(){
        IAppSettings appSettings = mock(IAppSettings.class);
        when(appSettings.getString("DataEndpoint")).thenReturn("Test_Data_Endpoint");

        ConnectionSettings settings = new ConnectionSettings(appSettings);

        assertEquals("Test_Data_Endpoint", settings.getDataEndpoint());
    }

    @Test
    public void test_ConnectionSettings_SetDataEndpoint(){
        IAppSettings appSettings = mock(IAppSettings.class);

        ConnectionSettings settings = new ConnectionSettings(appSettings);

        settings.setDataEndpoint("Test_Data_Endpoint");

        verify(appSettings).setString("DataEndpoint", "Test_Data_Endpoint");
    }

    @Test
    public void test_ConnectionSettings_GetDataCollection(){
        IAppSettings appSettings = mock(IAppSettings.class);
        when(appSettings.getString("DataCollection")).thenReturn("TestColl");

        ConnectionSettings settings = new ConnectionSettings(appSettings);

        assertEquals("TestColl", settings.getDataCollection());
    }

    @Test
    public void test_ConnectionSettings_SetDataCollection(){
        IAppSettings appSettings = mock(IAppSettings.class);

        ConnectionSettings settings = new ConnectionSettings(appSettings);

        settings.setDataCollection("TestColl");

        verify(appSettings).setString("DataCollection", "TestColl");
    }

    @Test
    public void test_ConnectionSettings_GetProductsEndpoint(){
        IAppSettings appSettings = mock(IAppSettings.class);
        when(appSettings.getString("ProductsEndpoint")).thenReturn("Products_Endpoint");

        ConnectionSettings settings = new ConnectionSettings(appSettings);

        assertEquals("Products_Endpoint", settings.getProductsEndpoint());
    }

    @Test
    public void test_ConnectionSettings_SetProductsEndpoint(){
        IAppSettings appSettings = mock(IAppSettings.class);

        ConnectionSettings settings = new ConnectionSettings(appSettings);

        settings.setProductsEndpoint("Products_Endpoint");

        verify(appSettings).setString("ProductsEndpoint", "Products_Endpoint");
    }
}
