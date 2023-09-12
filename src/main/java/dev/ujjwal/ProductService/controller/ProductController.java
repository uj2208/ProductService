package dev.ujjwal.ProductService.controller;
import dev.ujjwal.ProductService.thirdPartyClients.productService.fakestore.FakeStoreProductDto;
import dev.ujjwal.ProductService.dto.GenericProductDto;
import dev.ujjwal.ProductService.exception.NotFoundException;
import dev.ujjwal.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<GenericProductDto> getAllProducts() {
      return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public GenericProductDto deleteProductById(@PathVariable("id") Long id) {
     return productService.getProductAndThenDelete(id);
    }
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody GenericProductDto product) {
        return new ResponseEntity<>(productService.createProduct(product),HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public  GenericProductDto updateProductById(@PathVariable("id") Long id,@RequestBody FakeStoreProductDto product) {
         return  productService.updateProductById(id,product);
    }


}