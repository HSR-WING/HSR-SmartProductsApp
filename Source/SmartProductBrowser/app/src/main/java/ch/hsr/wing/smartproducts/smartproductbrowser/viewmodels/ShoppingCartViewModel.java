package ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;

import javax.inject.Inject;

import ch.hsr.wing.smartproducts.R;
import ch.hsr.wing.smartproducts.smartproductbrowser.IApp;
import ch.hsr.wing.smartproducts.smartproductbrowser.entities.CartItem;

public class ShoppingCartViewModel extends BaseViewModel {

    private final DecimalFormat df = new DecimalFormat("##0.00");

    private final IApp _app;

    @Inject
    public ShoppingCartViewModel(IApp app){
        this._app = app;
    }

    private final Timer timer = new Timer();

    @Override
    protected void onInit() {

    }

    @Override
    protected void onRefresh(){

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

    private Date _cartItemsTimestamp = null;
    public String getCartItemsTimestamp(){
        if(this._cartItemsTimestamp == null){
            return this._app.getString(R.string.updated_never);
        }
        return "";
    }

    public String getTotalAmount(){
        String value = df.format(0.0);
        return String.format("%s %s", this._app.getString(R.string.currency), value);
    }

    @Override
    protected void onHold(){

    }

    @Override
    protected void onDispose() {
        this.clearTimer();
    }

    private void clearTimer() {
        timer.cancel();
        timer.purge();
    }
}
