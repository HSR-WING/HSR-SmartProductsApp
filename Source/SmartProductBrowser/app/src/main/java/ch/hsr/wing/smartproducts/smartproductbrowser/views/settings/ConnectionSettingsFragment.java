package ch.hsr.wing.smartproducts.smartproductbrowser.views.settings;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import ch.hsr.wing.smartproducts.R;
import ch.hsr.wing.smartproducts.smartproductbrowser.di.DI;
import ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels.SettingsViewModel;

public class ConnectionSettingsFragment extends PreferenceFragmentCompat implements ISettingsUICallback {

    private SettingsViewModel _viewModel;
    private SettingsViewModelBinding _binding;

    private final Map<String, SummaryEntry> _summaries = new HashMap<>();

    @Inject
    ViewModelProvider.Factory _factory;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        DI.container(this).inject(this);
        setPreferencesFromResource(R.xml.views_settings, rootKey);
        this.init();

        this._viewModel = ViewModelProviders.of(this, this._factory).get(SettingsViewModel.class);
        this._viewModel.init();

        this._binding = new SettingsViewModelBinding(this._viewModel, this);
    }

    private void init(){
        this._summaries.put(this.getString(R.string.key_data_api_endpoint), new SummaryEntry(this.getString(R.string.settings_summary_dataapiendpoint), this.getString(R.string.settings_summary_dataapiendpoint_current)));
        this._summaries.put(this.getString(R.string.key_data_api_collection), new SummaryEntry(this.getString(R.string.settings_summary_dataapicollection), this.getString(R.string.settings_summary_dataapicollection_current)));
        this._summaries.put(this.getString(R.string.key_product_api_endpoint), new SummaryEntry(this.getString(R.string.settings_summary_productsapiendpoint), this.getString(R.string.settings_summary_productsapiendpoint_current)));
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

        this.refreshSummaryOf(text, value);
    }

    @Override
    public void refreshSummary(String key) {
        Preference preference = this.findPreference(key);
        String value = this.getPreferences().getString(key, "");

        this.refreshSummaryOf(preference, value);
    }

    private void refreshSummaryOf(Preference preference, String value){
        if(preference == null){
            return;
        }
        String summary = this.getSummaryFor(preference.getKey(), value);
        if(summary == null){
            return;
        }
        preference.setSummary(summary);
    }

    private String getSummaryFor(String key, String value){
        if(!this._summaries.containsKey(key)){
            return null;
        }
        SummaryEntry entry = this._summaries.get(key);
        if(value == null || value.trim().equals("")){
            return entry.getDefaultSummary();
        }
        return entry.getSummaryWithValue(value);
    }


    private <T extends Preference> T getPreferenceComponent(Class<T> c, String key){
        Preference component = this.findPreference(key);
        if(!c.isAssignableFrom(component.getClass())){
            throw new ClassCastException("Cannot cast preference component");
        }
        return (T)component;
    }
}
