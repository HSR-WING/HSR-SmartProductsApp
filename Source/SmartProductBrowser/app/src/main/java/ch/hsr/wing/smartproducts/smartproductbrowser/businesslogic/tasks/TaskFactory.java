package ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.tasks;

import javax.inject.Inject;

import ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.ICallbackHandler;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities.ResponseTypes;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.IApiClient;

public class TaskFactory implements ITaskFactory {

    @Inject
    public TaskFactory(){

    }

    @Override
    public ITask<Void> createPingTask(IApiClient client, ICallbackHandler<ResponseTypes> callback) {
        return new ApiPingTask(client, callback);
    }
}
