package ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote;

import java.util.List;
import java.util.UUID;

import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities.ProductDto;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities.ProductInfoDto;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote.entities.ContentResponse;

public interface IProductApiClient extends IApiClient {
    ContentResponse<List<ProductInfoDto>> getAllProducts();
    ContentResponse<ProductDto> getDetailsOfProductById(UUID productId);
}
