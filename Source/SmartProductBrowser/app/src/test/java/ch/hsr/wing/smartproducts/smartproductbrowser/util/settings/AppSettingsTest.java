package ch.hsr.wing.smartproducts.smartproductbrowser.util.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

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
            public String getString(int resource) {
                return null;
            }

            @Override
            public SharedPreferences getSettings() {
                return preferences;
            }

            @Override
            public Bitmap getImageFromDrawable(int resource) {
                return null;
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

    @Test
    public void test_AppSettings_GetInt(){
        SharedPreferences pref = mock(SharedPreferences.class);
        when(pref.getInt("TestKey", 0)).thenReturn(42);

        AppSettings settings = new AppSettings(this.toApp(pref));

        assertEquals(42, settings.getInt("TestKey"));
    }

    @Test
    public void test_AppSettings_SetInt(){
        SharedPreferences pref = mock(SharedPreferences.class);
        SharedPreferences.Editor editor = mock(SharedPreferences.Editor.class);
        when(pref.edit()).thenReturn(editor);

        AppSettings settings = new AppSettings(this.toApp(pref));

        settings.setInt("TestKey", 42);

        verify(pref).edit();
        verify(editor).putInt("TestKey", 42);
        verify(editor).apply();
    }
}
