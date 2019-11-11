package ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels;

import ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.ICallbackHandler;

public class NullCallbackHandler<T> implements ICallbackHandler<T> {

    private boolean _handled;

    public boolean wasHandled(){
        return this._handled;
    }

    @Override
    public void handle(T result) {
        this._handled = true;
    }
}
