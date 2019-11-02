package ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels;

import androidx.lifecycle.ViewModel;

import ch.hsr.wing.smartproducts.smartproductbrowser.util.settings.IConnectionSettings;

public class SettingsViewModel extends ViewModel {

    private final IConnectionSettings _settings;

    public SettingsViewModel(IConnectionSettings settings){
        this._settings = settings;
    }
}
