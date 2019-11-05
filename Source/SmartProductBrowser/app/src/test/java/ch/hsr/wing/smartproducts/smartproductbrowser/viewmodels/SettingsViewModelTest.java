package ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.hsr.wing.smartproducts.smartproductbrowser.util.settings.IConnectionSettings;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class SettingsViewModelTest {

    @Test
    public void tet_DataEndpoint_Init(){
        IConnectionSettings settings = mock(IConnectionSettings.class);
        when(settings.getDataEndpoint()).thenReturn("Data_Endpoint");

        SettingsViewModel vm = new SettingsViewModel(settings);
        vm.init();

        assertEquals("Data_Endpoint", vm.dataEndpoint.get());
    }

    @Test
    public void test_DataEndpoint_NoChanges(){
        IConnectionSettings settings = mock(IConnectionSettings.class);
        SettingsViewModel vm = new SettingsViewModel(settings);
        vm.init();

        vm.dataEndpoint.set("test");

        verify(settings, times(0)).setDataEndpoint(anyString());
    }

    @Test
    public void test_DataEndpoint_SaveChanges(){
        IConnectionSettings settings = mock(IConnectionSettings.class);
        SettingsViewModel vm = new SettingsViewModel(settings);
        vm.init();

        vm.dataEndpoint.set("test");

        vm.saveChanges();

        verify(settings).setDataEndpoint("test");
    }

    @Test
    public void tet_ProductsEndpoint_Init(){
        IConnectionSettings settings = mock(IConnectionSettings.class);
        when(settings.getProductsEndpoint()).thenReturn("Products_Endpoint");

        SettingsViewModel vm = new SettingsViewModel(settings);
        vm.init();

        assertEquals("Products_Endpoint", vm.productEndpoint.get());
    }

    @Test
    public void test_ProductEndpoint_NoChanges(){
        IConnectionSettings settings = mock(IConnectionSettings.class);
        SettingsViewModel vm = new SettingsViewModel(settings);
        vm.init();

        vm.productEndpoint.set("test");

        verify(settings, times(0)).setProductsEndpoint(anyString());
    }

    @Test
    public void test_ProductEndpoint_SaveChanges(){
        IConnectionSettings settings = mock(IConnectionSettings.class);
        SettingsViewModel vm = new SettingsViewModel(settings);
        vm.init();

        vm.productEndpoint.set("test");

        vm.saveChanges();

        verify(settings).setProductsEndpoint("test");
    }
}
