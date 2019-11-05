package ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels;

import androidx.databinding.ObservableField;

import javax.inject.Inject;

import ch.hsr.wing.smartproducts.smartproductbrowser.util.settings.IConnectionSettings;

public class SettingsViewModel extends BaseViewModel {

    private final IConnectionSettings _settings;

    public final ObservableField<String> dataEndpoint = new ObservableField<>();
    public final ObservableField<String> dataCollection = new ObservableField<>();
    public final ObservableField<String> productEndpoint = new ObservableField<>();

    @Inject
    public SettingsViewModel(IConnectionSettings settings){
        this._settings = settings;
    }

    @Override
    protected void onInit() {
        this.loadSettings();
    }

    private void loadSettings() {
        this.dataEndpoint.set(this._settings.getDataEndpoint());
        this.dataCollection.set(this._settings.getDataCollection());
        this.productEndpoint.set(this._settings.getProductsEndpoint());
    }

    public void saveChanges(){
        this.saveSettings();
    }

    private void saveSettings(){
        this._settings.setDataEndpoint(this.dataEndpoint.get());
        this._settings.setDataCollection(this.dataCollection.get());
        this._settings.setProductsEndpoint(this.productEndpoint.get());
    }

    @Override
    protected void onDispose() {

    }
}
