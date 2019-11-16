package ch.hsr.wing.smartproducts.smartproductbrowser.views.adapters;

import androidx.databinding.ViewDataBinding;

import ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels.IViewModelObserver;

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
