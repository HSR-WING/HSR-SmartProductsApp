package ch.ost.wing.smartproducts.smartproductbrowser.businesslogic.tasks;

import android.os.AsyncTask;

import ch.ost.wing.smartproducts.smartproductbrowser.businesslogic.ICallbackHandler;
import ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities.ResponseTypes;
import ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.remote.IApiClient;

class ApiPingTask extends AsyncTask<Void, Void, ResponseTypes> implements ITask {

    private final IApiClient _client;
    private final ICallbackHandler<ResponseTypes> _callback;

    public ApiPingTask(IApiClient client, ICallbackHandler<ResponseTypes> callback){
        this._client = client;
        this._callback = callback;
    }

    @Override
    protected ResponseTypes doInBackground(Void... voids) {
        return this._client.ping();
    }

    @Override
    protected void onPostExecute(ResponseTypes response){
        this._callback.handle(response);
    }

    @Override
    public void run() {
        this.execute();
    }
}
