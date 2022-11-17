package ch.ost.wing.smartproducts.shoppingcartapp.viewmodels;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.ost.wing.smartproducts.shoppingcartapp.businesslogic.ICallbackHandler;
import ch.ost.wing.smartproducts.shoppingcartapp.businesslogic.tasks.ITask;
import ch.ost.wing.smartproducts.shoppingcartapp.businesslogic.tasks.ITaskFactory;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities.ResponseTypes;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.IDataApiClient;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.IProductApiClient;
import ch.ost.wing.smartproducts.shoppingcartapp.util.settings.IConnectionSettings;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class SettingsViewModelTest {

    @Test
    public void test_SettingsViewModel_DataEndpoint_Init(){
        IConnectionSettings settings = mock(IConnectionSettings.class);
        when(settings.getDataEndpoint()).thenReturn("Data_Endpoint");

        SettingsViewModel vm = new SettingsViewModel(settings, mock(ITaskFactory.class), mock(IDataApiClient.class), mock(IProductApiClient.class));
        vm.init();

        assertEquals("Data_Endpoint", vm.dataEndpoint.get());
    }

    @Test
    public void test_SettingsViewModel_DataEndpoint_NoChanges(){
        IConnectionSettings settings = mock(IConnectionSettings.class);
        SettingsViewModel vm = new SettingsViewModel(settings, mock(ITaskFactory.class), mock(IDataApiClient.class), mock(IProductApiClient.class));
        vm.init();

        vm.dataEndpoint.set("test");

        verify(settings, times(0)).setDataEndpoint(anyString());
    }

    @Test
    public void test_SettingsViewModel_DataEndpoint_SaveChanges(){
        IConnectionSettings settings = mock(IConnectionSettings.class);
        SettingsViewModel vm = new SettingsViewModel(settings, mock(ITaskFactory.class), mock(IDataApiClient.class), mock(IProductApiClient.class));
        vm.init();

        vm.dataEndpoint.set("test");

        vm.saveChanges();

        verify(settings).setDataEndpoint("test");
    }

    @Test
    public void test_SettingsViewModel_DataCollection_Init(){
        IConnectionSettings settings = mock(IConnectionSettings.class);
        when(settings.getDataCollection()).thenReturn("TestColl");

        SettingsViewModel vm = new SettingsViewModel(settings, mock(ITaskFactory.class), mock(IDataApiClient.class), mock(IProductApiClient.class));
        vm.init();

        assertEquals("TestColl", vm.dataCollection.get());
    }

    @Test
    public void test_SettingsViewModel_DataCollection_NoChanges(){
        IConnectionSettings settings = mock(IConnectionSettings.class);
        SettingsViewModel vm = new SettingsViewModel(settings, mock(ITaskFactory.class), mock(IDataApiClient.class), mock(IProductApiClient.class));
        vm.init();

        vm.dataCollection.set("test");

        verify(settings, times(0)).setDataCollection(anyString());
    }

    @Test
    public void test_SettingsViewModel_DataCollection_SaveChanges(){
        IConnectionSettings settings = mock(IConnectionSettings.class);
        SettingsViewModel vm = new SettingsViewModel(settings, mock(ITaskFactory.class), mock(IDataApiClient.class), mock(IProductApiClient.class));
        vm.init();

        vm.dataCollection.set("TestColl");

        vm.saveChanges();

        verify(settings).setDataCollection("TestColl");
    }

    @Test
    public void test_SettingsViewModel_TestDataApi(){
        IConnectionSettings settings = mock(IConnectionSettings.class);
        ITaskFactory factory = mock(ITaskFactory.class);
        ITask task = mock(ITask.class);
        IDataApiClient client = mock(IDataApiClient.class);
        ICallbackHandler<ResponseTypes> callback = new NullCallbackHandler<>();
        when(factory.createPingTask(client, callback)).thenReturn(task);

        SettingsViewModel vm = new SettingsViewModel(settings, factory, client, mock(IProductApiClient.class));
        vm.init();

        vm.testDataApi(callback);

        verify(factory).createPingTask(client, callback);
    }

    @Test
    public void test_SettingsViewModel_TestProductApi(){
        IConnectionSettings settings = mock(IConnectionSettings.class);
        ITaskFactory factory = mock(ITaskFactory.class);
        ITask task = mock(ITask.class);
        IProductApiClient client = mock(IProductApiClient.class);
        ICallbackHandler<ResponseTypes> callback = new NullCallbackHandler<>();
        when(factory.createPingTask(client, callback)).thenReturn(task);

        SettingsViewModel vm = new SettingsViewModel(settings, factory, mock(IDataApiClient.class), client);
        vm.init();

        vm.testProductApi(callback);

        verify(factory).createPingTask(client, callback);
    }

    @Test
    public void tet_SettingsViewModel_ProductsEndpoint_Init(){
        IConnectionSettings settings = mock(IConnectionSettings.class);
        when(settings.getProductsEndpoint()).thenReturn("Products_Endpoint");

        SettingsViewModel vm = new SettingsViewModel(settings, mock(ITaskFactory.class), mock(IDataApiClient.class), mock(IProductApiClient.class));
        vm.init();

        assertEquals("Products_Endpoint", vm.productEndpoint.get());
    }

    @Test
    public void test_SettingsViewModel_ProductEndpoint_NoChanges(){
        IConnectionSettings settings = mock(IConnectionSettings.class);
        SettingsViewModel vm = new SettingsViewModel(settings, mock(ITaskFactory.class), mock(IDataApiClient.class), mock(IProductApiClient.class));
        vm.init();

        vm.productEndpoint.set("test");

        verify(settings, times(0)).setProductsEndpoint(anyString());
    }

    @Test
    public void test_SettingsViewModel_ProductEndpoint_SaveChanges(){
        IConnectionSettings settings = mock(IConnectionSettings.class);
        SettingsViewModel vm = new SettingsViewModel(settings, mock(ITaskFactory.class), mock(IDataApiClient.class), mock(IProductApiClient.class));
        vm.init();

        vm.productEndpoint.set("test");

        vm.saveChanges();

        verify(settings).setProductsEndpoint("test");
    }
}
