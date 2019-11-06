package ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote;

import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.entities.DataDto;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.entities.ResponseTypes;

public interface IDataApiClient {
    ResponseTypes ping();
    DataDto getLatest();
}
