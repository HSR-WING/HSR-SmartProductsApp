package ch.hsr.wing.smartproducts.smartproductbrowser.util.settings;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ConnectionSettingsTest {

    @Test
    public void test_getDataEndpoint(){
        IAppSettings appSettings = mock(IAppSettings.class);
        when(appSettings.getString("DataEndpoint")).thenReturn("Data_Endpoint");

        ConnectionSettings settings = new ConnectionSettings(appSettings);

        assertEquals("Data_Endpoint", settings.getDataEndpoint());
    }

    
}
