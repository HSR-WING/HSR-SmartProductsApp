package ch.hsr.wing.smartproducts.smartproductbrowser.di;

import ch.hsr.wing.smartproducts.smartproductbrowser.App;
import ch.hsr.wing.smartproducts.smartproductbrowser.util.settings.SettingsModule;
import ch.hsr.wing.smartproducts.smartproductbrowser.views.settings.ConnectionSettingsFragment;

public class DI {

    private static DIComponent component;

    public static void setup(App app){
        component = DaggerDIComponent.builder()
                .settingsModule(new SettingsModule(app.getSettings()))
                .build();
    }

    public static void inject(ConnectionSettingsFragment obj){
        component.inject(obj);
    }
}
