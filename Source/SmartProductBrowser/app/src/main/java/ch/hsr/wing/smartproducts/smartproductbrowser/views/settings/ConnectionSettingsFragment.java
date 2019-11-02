package ch.hsr.wing.smartproducts.smartproductbrowser.views.settings;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceFragmentCompat;

import javax.inject.Inject;

import ch.hsr.wing.smartproducts.R;
import ch.hsr.wing.smartproducts.smartproductbrowser.di.DI;
import ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels.SettingsViewModel;

public class ConnectionSettingsFragment extends PreferenceFragmentCompat {

    private SettingsViewModel _viewModel;

    @Inject
    public ViewModelProvider.Factory _factory;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        DI.inject(this);
        setPreferencesFromResource(R.xml.views_settings, rootKey);
        this._viewModel = ViewModelProviders.of(this, this._factory).get(SettingsViewModel.class);
    }
}
