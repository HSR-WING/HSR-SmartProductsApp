package ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.remote;

import ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities.ContentResponse;
import ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities.DataDto;

public interface IDataApiClient extends IApiClient {
    ContentResponse<DataDto> getLatest();
}
