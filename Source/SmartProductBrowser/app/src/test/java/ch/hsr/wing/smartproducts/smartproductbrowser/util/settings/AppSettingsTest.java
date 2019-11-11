package ch.hsr.wing.smartproducts.smartproductbrowser.util.settings;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Test;

import ch.hsr.wing.smartproducts.smartproductbrowser.IApp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AppSettingsTest {

    private IApp toApp(final SharedPreferences preferences){
        return new IApp() {
            @Override
            public Context getAppContext() {
                return null;
            }

            @Override
            public SharedPreferences getSettings() {
                return preferences;
            }
        };
    }

    @Test
    public void test_AppSettings_GetString(){
        SharedPreferences pref = mock(SharedPreferences.class);
        when(pref.getString("TestKey", "")).thenReturn("TestValue");

        AppSettings settings = new AppSettings(this.toApp(pref));

        assertEquals("TestValue", settings.getString("TestKey"));
    }

    @Test
    public void test_AppSettings_SetString(){
        SharedPreferences pref = mock(SharedPreferences.class);
        SharedPreferences.Editor editor = mock(SharedPreferences.Editor.class);
        when(pref.edit()).thenReturn(editor);

        AppSettings settings = new AppSettings(this.toApp(pref));

        settings.setString("TestKey", "TestValue");

        verify(pref).edit();
        verify(editor).putString("TestKey", "TestValue");
        verify(editor).apply();
    }
}
