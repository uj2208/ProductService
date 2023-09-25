package dev.ujjwal.ProductService.service;

import dev.ujjwal.ProductService.dto.ProductDto;
import dev.ujjwal.ProductService.exception.NotFoundException;

import java.util.List;

public interface ProductServiceApis {
    //getAllProducts
    List<ProductDto>getAllProducts();
    //getAllCategories
    List<String> getAllCategories();
    //getProductById
    ProductDto getProductById(String id) throws NotFoundException;
    //getProductByCategory
    List<ProductDto> getProductsByCategory(String category);
    //addonProduct
    ProductDto addonProduct(ProductDto productDto);
    //updateProduct
    ProductDto updateProduct(ProductDto productDto , String id);
    //deleteProduct
    ProductDto deleteProduct(String id);
}
