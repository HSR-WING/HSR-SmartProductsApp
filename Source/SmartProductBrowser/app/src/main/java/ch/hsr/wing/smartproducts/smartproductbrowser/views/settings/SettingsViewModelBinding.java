package ch.hsr.wing.smartproducts.smartproductbrowser.views.settings;

import android.content.SharedPreferences;
import android.content.res.Resources;

import androidx.databinding.ObservableField;

import java.util.HashMap;
import java.util.Map;

import ch.hsr.wing.smartproducts.R;
import ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels.SettingsViewModel;

public class SettingsViewModelBinding implements SharedPreferences.OnSharedPreferenceChangeListener {
    private final SettingsViewModel _viewModel;
    private final ISettingsUICallback _ui;

    private final Map<String, ObservableField<String>> _mappings = new HashMap<>();

    public SettingsViewModelBinding(SettingsViewModel vm, ISettingsUICallback callback){
        this._viewModel = vm;
        this._ui = callback;
        this.init();
    }

    private SharedPreferences getPreferences(){
        return this._ui.getPreferences();
    }

    private void init(){
        Resources res = this._ui.getResources();
        this._mappings.put(res.getString(R.string.key_data_api_endpoint), this._viewModel.dataEndpoint);
        this._mappings.put(res.getString(R.string.key_data_api_collection), this._viewModel.dataCollection);
        this._mappings.put(res.getString(R.string.key_product_api_endpoint), this._viewModel.productEndpoint);
    }

    public void enable(){
        this.refresh();
        this.refreshUI();
        this.getPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    private void refresh(){
        SharedPreferences.Editor edit = this.getPreferences().edit();
        for(Map.Entry<String, ObservableField<String>> entry : this._mappings.entrySet()){
            String value = entry.getValue().get();
            edit.putString(entry.getKey(), value);
        }
        edit.apply();
    }

    private void refreshUI(){
        for(Map.Entry<String, ObservableField<String>> entry : this._mappings.entrySet()){
            this._ui.refresh(entry.getKey());
        }
    }

    public void disable(){
        this.getPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(!this._mappings.containsKey(key)){
            return;
        }
        ObservableField<String> field = this._mappings.get(key);
        String value = sharedPreferences.getString(key, "");
        field.set(value);
        this._viewModel.saveChanges();

        this._ui.refreshSummary(key);
    }
}
