package ch.ost.wing.smartproducts.shoppingcartapp.views.adapters;

import androidx.databinding.ViewDataBinding;

import ch.ost.wing.smartproducts.shoppingcartapp.viewmodels.IViewModelObserver;

public class ViewModelObserver implements IViewModelObserver {

    private final ViewDataBinding _binding;

    public ViewModelObserver(ViewDataBinding binding){
        this._binding = binding;
    }

    @Override
    public void refreshUI() {
        this._binding.invalidateAll();
    }
}
