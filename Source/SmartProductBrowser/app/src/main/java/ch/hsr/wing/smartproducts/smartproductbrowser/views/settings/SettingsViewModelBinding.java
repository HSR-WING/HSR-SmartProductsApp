package ch.hsr.wing.smartproducts.smartproductbrowser.views.settings;

import android.content.SharedPreferences;
import android.content.res.Resources;

import androidx.databinding.ObservableField;
import androidx.preference.PreferenceManager;

import java.util.HashMap;
import java.util.Map;

import ch.hsr.wing.smartproducts.R;
import ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels.SettingsViewModel;

public class SettingsViewModelBinding implements SharedPreferences.OnSharedPreferenceChangeListener {
    private final SettingsViewModel _viewModel;
    private final PreferenceManager _preferences;

    private final Map<String, ObservableField<String>> _mappings = new HashMap<>();

    public SettingsViewModelBinding(SettingsViewModel vm, PreferenceManager preferences){
        this._viewModel = vm;
        this._preferences = preferences;
        this.init();
    }

    private void init(){
        Resources res = this._preferences.getContext().getResources();
        this._mappings.put(res.getString(R.string.key_data_api_endpoint), this._viewModel.dataEndpoint);
        this._mappings.put(res.getString(R.string.key_product_api_endpoint), this._viewModel.productEndpoint);
    }

    public void enable(){
        this._preferences.getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    public void disable(){
        this._preferences.getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(!this._mappings.containsKey(key)){
            return;
        }
        ObservableField<String> field = this._mappings.get(key);
        String value = sharedPreferences.getString(key, "");
        field.set(value);
    }
}
