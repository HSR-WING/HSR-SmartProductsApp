package ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels;

import java.util.Timer;

import javax.inject.Inject;

public class ShoppingCartViewModel extends BaseViewModel {

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
