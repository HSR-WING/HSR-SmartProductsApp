package ch.ost.wing.smartproducts.smartproductbrowser.businesslogic.shoppingcart;

import java.util.TimerTask;

import javax.inject.Inject;

import ch.ost.wing.smartproducts.smartproductbrowser.businesslogic.ICallbackHandler;
import ch.ost.wing.smartproducts.smartproductbrowser.businesslogic.tasks.ITaskFactory;
import ch.ost.wing.smartproducts.smartproductbrowser.entities.ShoppingCart;

public class DataReloadTimerTask extends TimerTask implements ICallbackHandler<ShoppingCart> {

    private final ITaskFactory _tasks;

    @Inject
    public DataReloadTimerTask(ITaskFactory factory){
        this._tasks = factory;
    }

    private IDataReloadHandler _handler;
    public void register(IDataReloadHandler handler){
        this._handler = handler;
    }

    public void unregister(){
        this._handler = null;
    }

    @Override
    public void run() {
        this._tasks.createLoadShoppingCartItemsTask(this).run();
    }

    @Override
    public void handle(ShoppingCart result) {
        if(this._handler == null){
            return;
        }
        this._handler.onUpdate(result);
    }
}
