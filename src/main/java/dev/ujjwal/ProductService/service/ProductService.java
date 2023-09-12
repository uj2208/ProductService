package dev.ujjwal.ProductService.service;

import dev.ujjwal.ProductService.thirdPartyClients.productService.fakestore.FakeStoreProductDto;
import dev.ujjwal.ProductService.dto.GenericProductDto;
import dev.ujjwal.ProductService.exception.NotFoundException;

import java.util.List;

public interface ProductService {
    GenericProductDto createProduct(GenericProductDto product);

    GenericProductDto getProductById(Long id) throws NotFoundException;
    List<GenericProductDto> getAllProducts();
    GenericProductDto getProductAndThenDelete(Long id);

    GenericProductDto updateProductById(Long id, FakeStoreProductDto product);

}
