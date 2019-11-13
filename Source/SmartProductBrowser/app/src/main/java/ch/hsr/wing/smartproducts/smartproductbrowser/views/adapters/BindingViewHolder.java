package ch.hsr.wing.smartproducts.smartproductbrowser.views.adapters;

import android.view.View;

public class BindingViewHolder<T> {
    private View _view;
    private T _binding;

    public BindingViewHolder(View view, T binding){
        this._view = view;
        this._binding = binding;
    }

    public View getView(){
        return this._view;
    }

    public T getBinding(){
        return this._binding;
    }
}
