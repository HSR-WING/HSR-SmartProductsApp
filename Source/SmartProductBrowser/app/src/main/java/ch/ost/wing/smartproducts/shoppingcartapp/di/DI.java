package ch.ost.wing.smartproducts.shoppingcartapp.di;

import android.app.Activity;
import android.app.Application;

import androidx.fragment.app.Fragment;

import ch.ost.wing.smartproducts.shoppingcartapp.App;
import ch.ost.wing.smartproducts.shoppingcartapp.AppModule;

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
