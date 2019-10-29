package ch.hsr.wing.smartproducts.smartproductbrowser.di;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelProviderFactory<T> implements ViewModelProvider.Factory {

    private T _viewModel;

    public ViewModelProviderFactory(T viewModel) {
        this._viewModel = viewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (!modelClass.isAssignableFrom(this._viewModel.getClass())) {
            throw new IllegalArgumentException("Cannot assign class");
        }
        return (T)this._viewModel;
    }
}
