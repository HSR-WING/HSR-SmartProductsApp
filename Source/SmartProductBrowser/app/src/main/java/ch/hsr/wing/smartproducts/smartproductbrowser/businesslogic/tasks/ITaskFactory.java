package ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.tasks;

import ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.ICallbackHandler;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities.ResponseTypes;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.IApiClient;

public interface ITaskFactory {
    ITask<Void> createPingTask(IApiClient client, ICallbackHandler<ResponseTypes> callback);
}
