package dev.ujjwal.ProductService.service;

import dev.ujjwal.ProductService.dto.CategoryDto;
import dev.ujjwal.ProductService.dto.ProductDto;
import dev.ujjwal.ProductService.models.Category;
import dev.ujjwal.ProductService.models.Price;
import dev.ujjwal.ProductService.models.Product;
import dev.ujjwal.ProductService.repositories.CategoryRepository;
import dev.ujjwal.ProductService.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductServiceApis{
    private  final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public SelfProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto>productDtos = new ArrayList<>();
        for(Product product : products){
            productDtos.add(convertToProductDto(product));
        }
        return productDtos;
    }

    @Override
    public List<String> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<String>categoryList = new ArrayList<>();
        for(Category category : categories){
            categoryList.add(category.getName());
        }
        return categoryList;
    }

    @Override
    public ProductDto getProductById(String id) {
          Product product = productRepository.findById(UUID.fromString(id)).orElse(null);
            if(product != null){
                return convertToProductDto(product);
            }
            return null;
    }

    @Transactional
    @Override
    public List<ProductDto> getProductsByCategory(String category) {
        Category category1 = categoryRepository.findByName(category);
        if(category1!= null){
            List<Product>products = category1.getProducts();
            List<ProductDto>productDtos = new ArrayList<>();
            for(Product product : products){
                productDtos.add(convertToProductDto(product));
            }
            return productDtos;
        }
        return null;
    }

    @Override
    public ProductDto addonProduct(ProductDto productDto) {
        Product product = convertToProduct(productDto);
        product = productRepository.save(product);
        return convertToProductDto(product);
    }

    @Transactional
    @Override
    public ProductDto updateProduct(ProductDto productDto, String id) {
        // Retrieve the existing product by ID
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));

        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();

            // Update the properties of the existing product based on the DTO
            existingProduct.setTitle(productDto.getTitle());
            existingProduct.setDescription(productDto.getDescription());

            // Update the product's price
            Price price = existingProduct.getPrice();
            if (price == null) {
                price = new Price();
            }
            price.setPrice(productDto.getPrice());
            price.setCurrency(productDto.getCurrency());
            existingProduct.setPrice(price);

            existingProduct.setImage(productDto.getImage());

            CategoryDto categoryDto = productDto.getCategory();
            Category category = null;

            // Check if the category already exists by name
            if (categoryDto != null && categoryDto.getName() != null) {
                category = categoryRepository.findByName(categoryDto.getName());
            }

            // If the category doesn't exist, create a new one
            if (category == null) {
                category = new Category();
                category.setName(categoryDto.getName());
            }

            // Add the product to the category and set the category for the product
            category.getProducts().add(existingProduct);
            existingProduct.setCategory(category);

            // Save the updated product
            existingProduct = productRepository.save(existingProduct);

            return convertToProductDto(existingProduct);
        }

        return null;  // Product with the given ID not found
    }

    @Transactional
    @Override
    public ProductDto deleteProduct(String id) {
        Product product = productRepository.findById(UUID.fromString(id)).orElse(null);
        if(product != null){
            productRepository.deleteById(UUID.fromString(id));
            return convertToProductDto(product);
        }
        return null;
    }
   private Product convertToProduct(ProductDto productDto){
     Product product = new Product();
     product.setTitle(productDto.getTitle());
     product.setDescription(productDto.getDescription());
     Price price = new Price();
     price.setPrice(productDto.getPrice());
     price.setCurrency(productDto.getCurrency());
     product.setPrice(price);
     product.setImage(productDto.getImage());
     CategoryDto categoryDto = productDto.getCategory();
     Category category = null;
       if (categoryDto != null && categoryDto.getName() != null) {

           category = categoryRepository.findByName(categoryDto.getName());
       }
       if (category == null) {
           category = new Category();
           category.setName(categoryDto.getName());
       }
       category.getProducts().add(product);
       product.setCategory(category);

     product.setCategory(category);
     return product;
    }
    private  ProductDto convertToProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        Price price = product.getPrice();
        productDto.setPrice(price.getPrice());
        productDto.setCurrency(price.getCurrency());
        productDto.setImage(product.getImage());
        Category category = product.getCategory();
        List<Product>productList  = category.getProducts();
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        productDto.setCategory(categoryDto);
        return productDto;
    }

}
