package ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic;

import ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.shoppingcart.DataReloadTimerTask;
import ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.tasks.ITaskFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class TimerModule {

    @Provides
    public DataReloadTimerTask createReloadDataTask(ITaskFactory tasks){
        return new DataReloadTimerTask(tasks);
    }
}
