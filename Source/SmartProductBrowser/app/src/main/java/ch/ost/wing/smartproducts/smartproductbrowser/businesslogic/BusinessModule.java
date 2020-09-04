package ch.ost.wing.smartproducts.smartproductbrowser.businesslogic;

import ch.ost.wing.smartproducts.smartproductbrowser.businesslogic.shoppingcart.DefaultShoppingCartConverter;
import ch.ost.wing.smartproducts.smartproductbrowser.businesslogic.shoppingcart.IDataDtoConverter;
import ch.ost.wing.smartproducts.smartproductbrowser.businesslogic.tasks.ITaskFactory;
import ch.ost.wing.smartproducts.smartproductbrowser.businesslogic.tasks.TaskFactory;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class BusinessModule {

    @Binds
    public abstract ITaskFactory getFactory(TaskFactory factory);

    @Binds
    public abstract IDataDtoConverter getConverter(DefaultShoppingCartConverter converter);
}
