package ch.ost.wing.smartproducts.smartproductbrowser.di;

import android.app.Activity;
import android.app.Application;

import androidx.fragment.app.Fragment;

import ch.ost.wing.smartproducts.smartproductbrowser.App;
import ch.ost.wing.smartproducts.smartproductbrowser.AppModule;

public class DI {

    public static IContainer setup(App app){
        return DaggerIContainer.builder()
                .appModule(new AppModule(app))
                .build();
    }

    public static IContainer container(Activity activity){
        return getContainerOf(activity.getApplication());
    }

    public static IContainer container(Fragment fragment){
        return getContainerOf(fragment.getActivity().getApplication());
    }

    private static IContainer getContainerOf(Application application) {
        App app = (application instanceof App ? (App)application : null);
        if(app == null){
            throw new DIException("Application is not not type App. Cannot get DIContainer.");
        }
        return app.getContainer();
    }
}
