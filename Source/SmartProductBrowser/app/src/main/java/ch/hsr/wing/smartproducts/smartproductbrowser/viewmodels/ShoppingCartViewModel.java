package ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels;

import java.text.DecimalFormat;
import java.util.Timer;

import javax.inject.Inject;

public class ShoppingCartViewModel extends BaseViewModel {

    private final DecimalFormat df = new DecimalFormat("###.00");

    @Inject
    public ShoppingCartViewModel(){

    }

    private final Timer timer = new Timer();

    @Override
    protected void onInit() {

    }

    @Override
    protected void onRefresh(){

    }

    public String getTotalAmount(){
        return df.format(0.0);
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
