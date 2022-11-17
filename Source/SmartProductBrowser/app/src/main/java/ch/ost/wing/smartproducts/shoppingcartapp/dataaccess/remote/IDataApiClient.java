package ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote;

import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities.ContentResponse;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities.DataDto;

public interface IDataApiClient extends IApiClient {
    ContentResponse<DataDto> getLatest();
}
