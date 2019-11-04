package ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels;

import androidx.lifecycle.ViewModelProvider;

import ch.hsr.wing.smartproducts.smartproductbrowser.di.ViewModelProviderFactory;
import ch.hsr.wing.smartproducts.smartproductbrowser.util.settings.IConnectionSettings;
import dagger.Module;
import dagger.Provides;

@Module
public class SettingsViewModelModule {

    @Provides
    public SettingsViewModel getSettingsViewModel(IConnectionSettings settings){
        return new SettingsViewModel(settings);
    }

    @Provides
    public ViewModelProvider.Factory getSettingsViewModelFactory(SettingsViewModel vm){
        return new ViewModelProviderFactory<>(vm);
    }

}
