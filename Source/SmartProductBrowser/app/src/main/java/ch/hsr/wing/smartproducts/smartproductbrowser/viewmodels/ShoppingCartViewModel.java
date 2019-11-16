package ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;
import java.util.Timer;

import javax.inject.Inject;
import javax.inject.Provider;

import ch.hsr.wing.smartproducts.R;
import ch.hsr.wing.smartproducts.smartproductbrowser.IApp;
import ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.shoppingcart.DataReloadTimerTask;
import ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.shoppingcart.IDataReloadHandler;
import ch.hsr.wing.smartproducts.smartproductbrowser.entities.CartItem;
import ch.hsr.wing.smartproducts.smartproductbrowser.entities.ShoppingCart;

public class ShoppingCartViewModel extends BaseViewModel implements IDataReloadHandler {

    private final DecimalFormat df = new DecimalFormat("##0.00");

    private final Provider<DataReloadTimerTask> _reloadTaskFactory;
    private final IApp _app;

    @Inject
    public ShoppingCartViewModel(Provider<DataReloadTimerTask> taskFactory, IApp app){
        this._reloadTaskFactory = taskFactory;
        this._app = app;
    }

    private final Timer _timer = new Timer();
    private final SimpleDateFormat _dateFormat = new SimpleDateFormat("dd.MM.YYYY HH:mm:ss");

    @Override
    protected void onInit() {
        this._dateFormat.setTimeZone(TimeZone.getDefault());
    }

    private static final int TIMER_RATE_IN_SECONDS = 5;

    private DataReloadTimerTask _reloadTask;

    @Override
    protected void onRefresh(){
        this._reloadTask = this._reloadTaskFactory.get();
        this._reloadTask.register(this);
        this._timer.scheduleAtFixedRate(this._reloadTask, 0, TIMER_RATE_IN_SECONDS * 1000);
    }

    private ShoppingCart _current;
    @Override
    public void onUpdate(ShoppingCart cart) {
        if(cart == null){
            return;
        }
        this._current = cart;
        this.refreshBindings();
    }

    private IViewModelObserver _observer;
    public void bind(IViewModelObserver observer){
        this._observer = observer;
    }

    public void unbind(){
        this._observer = null;
    }

    public void refreshUI(){
        if(this._observer == null){
            return;
        }
        this._observer.refreshUI();
    }

    private final Set<IAdapterBinding<CartItem>> _bindings = new HashSet<>();
    public void bind(IAdapterBinding<CartItem> binding){
        if(binding == null){
            return;
        }
        this._bindings.add(binding);
    }

    public void unbind(IAdapterBinding<CartItem> binding){
        if(binding == null){
            return;
        }
        this._bindings.remove(binding);
    }

    private void refreshBindings(){
        this.refreshUI();
        for(IAdapterBinding<CartItem> binding : this._bindings){
            binding.refresh(this._current.getItems());
        }
    }

    public String getCartItemsTimestamp(){
        if(this._current == null){
            return this._app.getString(R.string.updated_never);
        }
        return this._dateFormat.format(this._current.getTimestamp());
    }

    public String getTotalAmount(){
        double total = this.sumTotalAmountOfCart();
        return String.format("%s %s", this._app.getString(R.string.currency), df.format(total));
    }

    private double sumTotalAmountOfCart(){
        if(this._current == null){
            return 0.0;
        }
        double total = 0;
        for(CartItem item : this._current.getItems()){
            total += item.getNumber() * item.getProduct().getPrice();
        }
        return total;
    }

    @Override
    protected void onHold(){
        this.clearTimer();
    }

    @Override
    protected void onDispose() {
        this.clearTimer();
    }

    private void clearTimer() {
        if(this._reloadTask != null){
            this._reloadTask.unregister();
        }
        this._timer.cancel();
        this._timer.purge();
    }

}
