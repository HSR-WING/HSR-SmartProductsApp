package ch.hsr.wing.smartproducts.smartproductbrowser.di;

import ch.hsr.wing.smartproducts.smartproductbrowser.App;
import ch.hsr.wing.smartproducts.smartproductbrowser.util.settings.SettingsModule;

public class DI {

    private static DIComponent component;

    public static void setup(App app){
        component = DaggerDIComponent.builder()
                .settingsModule(new SettingsModule(app.getSettings()))
                .build();
    }

    public static void inject(Object obj){
        component.inject(obj);
    }
}
