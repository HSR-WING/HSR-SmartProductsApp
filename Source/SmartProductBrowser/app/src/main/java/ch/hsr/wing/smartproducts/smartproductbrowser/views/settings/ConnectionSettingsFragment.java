package ch.hsr.wing.smartproducts.smartproductbrowser.views.settings;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import javax.inject.Inject;

import ch.hsr.wing.smartproducts.R;
import ch.hsr.wing.smartproducts.smartproductbrowser.di.DI;
import ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels.SettingsViewModel;

public class ConnectionSettingsFragment extends PreferenceFragmentCompat implements ISettingsUICallback {

    private SettingsViewModel _viewModel;
    private SettingsViewModelBinding _binding;

    @Inject
    ViewModelProvider.Factory _factory;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        DI.container(this).inject(this);
        setPreferencesFromResource(R.xml.views_settings, rootKey);
        this._viewModel = ViewModelProviders.of(this, this._factory).get(SettingsViewModel.class);
        this._viewModel.init();

        this._binding = new SettingsViewModelBinding(this._viewModel, this);
    }

    @Override
    public void onResume(){
        super.onResume();
        this._binding.enable();
    }

    @Override
    public void onPause(){
        super.onPause();
        this._binding.disable();
    }

    @Override
    public SharedPreferences getPreferences() {
        return this.getPreferenceManager().getSharedPreferences();
    }

    @Override
    public void refresh(String key) {
        EditTextPreference text = this.getPreferenceComponent(EditTextPreference.class, key);
        String value = this.getPreferences().getString(key, "");
        text.setText(value);
    }

    private <T extends Preference> T getPreferenceComponent(Class<T> c, String key){
        Preference component = this.findPreference(key);
        if(!c.isAssignableFrom(component.getClass())){
            throw new ClassCastException("Cannot cast preference component");
        }
        return (T)component;
    }
}
