package ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import ch.hsr.wing.smartproducts.smartproductbrowser.util.settings.IConnectionSettings;

public class SettingsViewModel extends ViewModel {

    private final IConnectionSettings _settings;

    @Inject
    public SettingsViewModel(IConnectionSettings settings){
        this._settings = settings;
    }
}
