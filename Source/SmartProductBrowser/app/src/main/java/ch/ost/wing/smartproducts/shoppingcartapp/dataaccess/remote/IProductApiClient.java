package ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote;

import java.util.List;
import java.util.UUID;

import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities.ProductDto;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities.ProductInfoDto;
import ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities.ContentResponse;

public interface IProductApiClient extends IApiClient {
    ContentResponse<List<ProductInfoDto>> getAllProducts();
    ContentResponse<ProductDto> getDetailsOfProductById(UUID productId);
}
