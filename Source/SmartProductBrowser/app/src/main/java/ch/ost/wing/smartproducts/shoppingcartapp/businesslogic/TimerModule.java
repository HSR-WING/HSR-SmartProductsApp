package ch.ost.wing.smartproducts.shoppingcartapp.businesslogic;

import ch.ost.wing.smartproducts.shoppingcartapp.businesslogic.shoppingcart.DataReloadTimerTask;
import ch.ost.wing.smartproducts.shoppingcartapp.businesslogic.tasks.ITaskFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class TimerModule {

    @Provides
    public DataReloadTimerTask createReloadDataTask(ITaskFactory tasks){
        return new DataReloadTimerTask(tasks);
    }
}
