package ch.ost.wing.smartproducts.shoppingcartapp;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final App _app;

    public AppModule(App app){
        this._app = app;
    }

    @Provides
    public IApp getApp(){
        return new AppWrapper(this._app);
    }
}
