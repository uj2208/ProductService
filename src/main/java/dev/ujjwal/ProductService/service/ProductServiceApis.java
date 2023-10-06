package dev.ujjwal.ProductService.service;

import dev.ujjwal.ProductService.dto.ProductDto;
import dev.ujjwal.ProductService.exception.InValidPatternException;
import dev.ujjwal.ProductService.exception.NotFoundException;

import java.util.List;

public interface ProductServiceApis {
    //getAllProducts
    List<ProductDto>getAllProducts();
    //getAllCategories
    List<String> getAllCategories();
    //getProductById
    ProductDto getProductById(String id) throws NotFoundException, InValidPatternException;
    //getProductByCategory
    List<ProductDto> getProductsByCategory(String category) throws NotFoundException;
    //addonProduct
    ProductDto addonProduct(ProductDto productDto);
    //updateProduct
    ProductDto updateProduct(ProductDto productDto , String id) throws NotFoundException;
    //deleteProduct
    ProductDto deleteProduct(String id) throws NotFoundException;
}
