package ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote;

import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities.DataDto;

public interface IDataApiClient extends IApiClient {
    DataDto getLatest();
}
