package ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels;

import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel extends ViewModel {

    private boolean _isInitialized;

    public boolean isInitialized(){
        return this._isInitialized;
    }

    public void init(){
        if(this.isInitialized()){
            return;
        }
        this.onInit();
        this._isInitialized = true;
    }

    protected abstract void onInit();
}
