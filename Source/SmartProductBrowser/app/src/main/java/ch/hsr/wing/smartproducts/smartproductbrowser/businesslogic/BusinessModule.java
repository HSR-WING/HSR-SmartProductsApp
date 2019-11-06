package ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic;

import ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.tasks.ITaskFactory;
import ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.tasks.TaskFactory;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class BusinessModule {

    @Binds
    public abstract ITaskFactory getFactory(TaskFactory factory);
}
