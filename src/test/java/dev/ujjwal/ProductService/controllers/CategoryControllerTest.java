package dev.ujjwal.ProductService.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.ujjwal.ProductService.controller.CategoryController;
import dev.ujjwal.ProductService.dto.CategoryDto;
import dev.ujjwal.ProductService.dto.ProductDto;
import dev.ujjwal.ProductService.exception.InValidPatternException;
import dev.ujjwal.ProductService.exception.NotFoundException;
import dev.ujjwal.ProductService.service.ProductServiceApis;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(name = "selfProductServiceImpl")
    private ProductServiceApis productServiceApis;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /product-service - empty list")
    void getAllProducts() throws Exception {
        when(productServiceApis.getAllProducts()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/product-service"))
                .andExpect(status().isOk()) // Expecting 200 OK
                .andExpect(content().json("[]")); // Expecting an empty JSON array
    }
    @Test
    @DisplayName("GET /product-service - with values")
    void  returnsListOfProductsWhenProductsExist() throws Exception{
    List<ProductDto> productDtoList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ProductDto product = new ProductDto();
            product.setId(UUID.randomUUID());
            product.setTitle("Product " + (i + 1));
            product.setDescription("Description for product " + (i + 1));
            product.setImage("ImageURL" + (i + 1));
            product.setCurrency("USD");
            product.setPrice(10.99 * (i + 1));
            productDtoList.add(product);
        }
        when(productServiceApis.getAllProducts()).thenReturn(productDtoList);
        mockMvc.perform(get("/product-service"))
                .andExpect(status().isOk()) // Expecting 200 OK
                .andExpect(content().json(objectMapper.writeValueAsString(productDtoList)));


    }
    @Test
    @DisplayName("GET /product-service/categories")
    void getCategoriesTest() throws Exception{
        List<String>categories = List.of("Laptop","Mobile","Tablet");
        when(productServiceApis.getAllCategories()).thenReturn(categories);
        mockMvc.perform(get("/product-service/categories"))
                .andExpect(status().isOk()) // Expecting 200 OK
                .andExpect(content().json(objectMapper.writeValueAsString(categories)));
    }
    @Test
    @DisplayName("GET /product-service/id/{id}")
    void getProductByIdTest() throws Exception{
        ProductDto productDto = new ProductDto();
        productDto.setId(UUID.randomUUID());
        productDto.setTitle("Product 1");
        productDto.setDescription("Description for product 1");
        productDto.setImage("ImageURL1");
        productDto.setCurrency("USD");
        productDto.setPrice(10.99);
        when(productServiceApis.getProductById(productDto.getId().toString())).thenReturn(productDto);
        mockMvc.perform(get("/product-service/id/{id}",productDto.getId()))
                .andExpect(status().isOk()) // Expecting 200 OK
                .andExpect(content().json(objectMapper.writeValueAsString(productDto)));
    }

    @Test
    @DisplayName("GET /product-service/{category}")
    void getProductByCategoryTest() throws Exception{
        List<ProductDto> productDtoList = new ArrayList<>();
        CategoryDto CategoryDto = new CategoryDto();
        CategoryDto.setId(UUID.randomUUID());
        CategoryDto.setName("Laptop");
        for (int i = 0; i < 3; i++) {
            ProductDto product = new ProductDto();
            product.setId(UUID.randomUUID());
            product.setTitle("Product " + (i + 1));
            product.setDescription("Description for product " + (i + 1));
            product.setImage("ImageURL" + (i + 1));
            product.setCurrency("USD");
            product.setPrice(10.99 * (i + 1));
            product.setCategory(CategoryDto);
            productDtoList.add(product);
        }
        when(productServiceApis.getProductsByCategory("Laptop")).thenReturn(productDtoList);
        mockMvc.perform(get("/product-service/{category}","Laptop"))
                .andExpect(status().isOk()) // Expecting 200 OK
                .andExpect(content().json(objectMapper.writeValueAsString(productDtoList)));
    }


    @Test
    @DisplayName("GET /product-service/id/{id} - InValidPatternException")
    void getProductByIdTestInValidPatternException()throws  Exception {
        String invalidUuid = "1234";
        when(productServiceApis.getProductById(invalidUuid)).thenThrow(new InValidPatternException("ID :"+invalidUuid+" is not valid"));
        mockMvc.perform(get("/product-service/id/{id}",invalidUuid))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("ID :"+invalidUuid+" is not valid"));
    }

    @Test
    @DisplayName("GET /product-service/id/{id} - NotFoundException")
    void getProductByIdTestNotFoundException()throws Exception{
       String uuid = UUID.randomUUID().toString();
       when(productServiceApis.getProductById(uuid)).thenThrow( new NotFoundException("Product with  id "+uuid+" not found"));
        mockMvc.perform(get("/product-service/id/{id}",uuid))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value("Product with  id "+uuid+" not found"));
    }

    @Test
    @DisplayName("Delete /product-service/id/{id}")
    void deleteProductByIdTest() throws Exception{
        ProductDto productDto = new ProductDto();
        productDto.setId(UUID.randomUUID());
        productDto.setTitle("Product 1");
        productDto.setDescription("Description for product 1");
        productDto.setImage("ImageURL1");
        productDto.setCurrency("USD");
        productDto.setPrice(10.99);
        when(productServiceApis.deleteProduct(productDto.getId().toString())).thenReturn(productDto);
        mockMvc.perform(delete("/product-service/{id}",productDto.getId()))
                .andExpect(status().isOk()) // Expecting 200 OK
                .andExpect(content().json(objectMapper.writeValueAsString(productDto)));
    }




//    {
//        "title": "Smartphone Model C",
//            "description": "An entry-level smartphone with essential features.",
//            "image": "https://example.com/smartphone_modelC.jpg",
//            "category": {
//        "name": "Smartphone"
//    },
//        "currency": "USD",
//            "price": 249.99
//    }


}
