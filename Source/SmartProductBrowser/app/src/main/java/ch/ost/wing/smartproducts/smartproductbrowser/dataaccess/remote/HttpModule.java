package ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.remote;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class HttpModule {

    @Provides
    public OkHttpClient getHttpClient(){
        return new OkHttpClient();
    }
}
