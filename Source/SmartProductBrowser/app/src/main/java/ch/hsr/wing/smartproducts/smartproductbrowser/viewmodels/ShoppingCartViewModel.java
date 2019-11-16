package ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels;

import java.text.DecimalFormat;
import java.util.Timer;

import javax.inject.Inject;

import ch.hsr.wing.smartproducts.R;
import ch.hsr.wing.smartproducts.smartproductbrowser.IApp;

public class ShoppingCartViewModel extends BaseViewModel {

    private final DecimalFormat df = new DecimalFormat("###.00");

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
