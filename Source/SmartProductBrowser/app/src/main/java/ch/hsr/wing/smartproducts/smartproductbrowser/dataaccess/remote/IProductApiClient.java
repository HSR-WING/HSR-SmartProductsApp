package ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.remote;

import java.util.List;
import java.util.UUID;

import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.entities.ProductDto;
import ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.entities.ProductInfoDto;

public interface IProductApiClient extends IApiClient {
    List<ProductInfoDto> getAllProducts();
    ProductDto getDetailsOfProductById(UUID productId);
}
