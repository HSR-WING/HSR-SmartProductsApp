package ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels;

import androidx.databinding.ObservableField;

import javax.inject.Inject;

import ch.hsr.wing.smartproducts.smartproductbrowser.util.settings.IConnectionSettings;

public class SettingsViewModel extends BaseViewModel {

    private final IConnectionSettings _settings;

    public ObservableField<String> dataEndpoint = new ObservableField<>();
    public ObservableField<String> productEndpoint = new ObservableField<>();

    @Inject
    public SettingsViewModel(IConnectionSettings settings){
        this._settings = settings;
    }

    @Override
    protected void onInit() {
        dataEndpoint.set(this._settings.getDataEndpoint());
        productEndpoint.set(this._settings.getProductsEndpoint());
    }

    @Override
    protected void onDispose() {

    }
}
