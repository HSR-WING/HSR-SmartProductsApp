package ch.hsr.wing.smartproducts.smartproductbrowser.di;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

public class ViewModelProviderFactory implements ViewModelProvider.Factory {

    private Map<Class<? extends ViewModel>, Provider<ViewModel>> _factories;

    @Inject
    public ViewModelProviderFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> factories) {
        this._factories = factories;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(!this._factories.containsKey(modelClass)){
            throw new IllegalArgumentException("ViewModel of type " + modelClass.getName() + " cannot be injected.");
        }
        Provider<ViewModel> factory = this._factories.get(modelClass);
        return (T)factory.get();
        /*if (!modelClass.isAssignableFrom(this._viewModel.getClass())) {
            throw new IllegalArgumentException("Cannot assign class");
        }
        return (T)this._viewModel;*/
    }
}
