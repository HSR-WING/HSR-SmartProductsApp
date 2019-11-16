package ch.hsr.wing.smartproducts.smartproductbrowser.views.adapters;

import android.view.View;

import androidx.databinding.ViewDataBinding;

public class BindingViewHolder<T extends ViewDataBinding> {
    private T _binding;

    public BindingViewHolder(T binding){
        this._binding = binding;
    }

    public View getView(){
        return this._binding.getRoot();
    }

    public T getBinding(){
        return this._binding;
    }
}
