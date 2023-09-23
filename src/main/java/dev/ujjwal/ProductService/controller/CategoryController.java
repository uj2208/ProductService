package dev.ujjwal.ProductService.controller;
import dev.ujjwal.ProductService.dto.ProductDto;
import dev.ujjwal.ProductService.service.ProductServiceApis;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product-service")
public class CategoryController {
    private  ProductServiceApis productServiceApis;
    public CategoryController(@Qualifier("selfProductServiceImpl")ProductServiceApis productServiceApis){
        this.productServiceApis = productServiceApis;
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDto product) {
        return new ResponseEntity<>(productServiceApis.addonProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("{category}")
       public ResponseEntity<?> getProductsByCategory(@PathVariable("category") String category) {
            return new ResponseEntity<>(productServiceApis.getProductsByCategory(category), HttpStatus.OK);
        }

     @GetMapping()
         public ResponseEntity<?> getAllProducts() {
                return new ResponseEntity<>(productServiceApis.getAllProducts(), HttpStatus.OK);
          }
      @GetMapping("/categories")
          public ResponseEntity<?> getAllCategories() {
                return new ResponseEntity<>(productServiceApis.getAllCategories(), HttpStatus.OK);
          }
       @GetMapping("/id/{id}")
           public ResponseEntity<?> getProductById(@PathVariable("id") String id) {
                return new ResponseEntity<>(productServiceApis.getProductById(id), HttpStatus.OK);
           }
        @PutMapping("{id}")
            public ResponseEntity<?> updateProductById(@PathVariable("id") String id,@RequestBody ProductDto product) {
                return new ResponseEntity<>(productServiceApis.updateProduct(product,id), HttpStatus.OK);
            }
        @DeleteMapping("{id}")
            public ResponseEntity<?> deleteProductById(@PathVariable("id") String id) {
                return new ResponseEntity<>(productServiceApis.deleteProduct(id), HttpStatus.OK);
            }
}

