package ch.ost.wing.smartproducts.shoppingcartapp.viewmodels;

import androidx.databinding.ObservableField;

import javax.inject.Inject;

import ch.ost.wing.smartproducts.shoppingcartapp.businesslogic.ICallbackHandler;
import ch.ost.wing.smartproducts.shoppingcartapp.businesslogic.tasks.ITask;
import ch.ost.wing.smartproducts.shoppingcartapp.businesslogic.tasks.ITaskFactory;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities.ResponseTypes;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.IDataApiClient;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.IProductApiClient;
import ch.ost.wing.smartproducts.shoppingcartapp.util.settings.IConnectionSettings;

public class SettingsViewModel extends BaseViewModel {

    private final IConnectionSettings _settings;
    private final IDataApiClient _dataApiClient;
    private final IProductApiClient _productApiClient;

    private final ITaskFactory _tasks;

    public final ObservableField<String> dataEndpoint = new ObservableField<>();
    public final ObservableField<String> dataCollection = new ObservableField<>();
    public final ObservableField<String> productEndpoint = new ObservableField<>();

    @Inject
    public SettingsViewModel(IConnectionSettings settings, ITaskFactory tasks, IDataApiClient dataApiClient, IProductApiClient productApiClient){
        this._settings = settings;
        this._tasks = tasks;
        this._dataApiClient = dataApiClient;
        this._productApiClient = productApiClient;
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

    @Override
    protected void onRefresh(){
        
    }

    @Override
    protected void onHold(){

    }

    public void saveChanges(){
        this.saveSettings();
    }

    private void saveSettings(){
        this._settings.setDataEndpoint(this.dataEndpoint.get());
        this._settings.setDataCollection(this.dataCollection.get());
        this._settings.setProductsEndpoint(this.productEndpoint.get());
    }

    public void testDataApi(ICallbackHandler<ResponseTypes> callback){
        ITask task = this._tasks.createPingTask(this._dataApiClient, callback);
        task.run();
    }

    public void testProductApi(ICallbackHandler<ResponseTypes> callback){
        ITask task = this._tasks.createPingTask(this._productApiClient, callback);
        task.run();
    }

    @Override
    protected void onDispose() {

    }
}
