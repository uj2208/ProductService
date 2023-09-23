package dev.ujjwal.ProductService.service;
import dev.ujjwal.ProductService.thirdPartyClients.productService.fakestore.FakeStoreProductDto;
import dev.ujjwal.ProductService.dto.GenericProductDto;
import dev.ujjwal.ProductService.exception.NotFoundException;
import dev.ujjwal.ProductService.thirdPartyClients.productService.fakestore.FakeStoryProductServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements  ProductService{

    private final FakeStoryProductServiceClient fakeStoryProductServiceClient;

    @Autowired
    public  FakeStoreProductService (FakeStoryProductServiceClient fakeStoryProductServiceClient){
        this.fakeStoryProductServiceClient = fakeStoryProductServiceClient;
    }
    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
      return getGenericProductDto(fakeStoryProductServiceClient.createProduct(product));
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return getGenericProductDto(fakeStoryProductServiceClient.getProductById(id));
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto: fakeStoryProductServiceClient.getAllProducts()) {
            genericProductDtos.add(getGenericProductDto(fakeStoreProductDto));
        }
        return genericProductDtos;
    }

    @Override
    public GenericProductDto getProductAndThenDelete(Long id) {
        return getGenericProductDto(fakeStoryProductServiceClient.getProductAndThenDelete(id));
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto product) {
        return getGenericProductDto(fakeStoryProductServiceClient.updateProductById(id,product));
    }


    private GenericProductDto getGenericProductDto(FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto product = new GenericProductDto();
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setId(fakeStoreProductDto.getId());
        product.setCategory(fakeStoreProductDto.getCategory());
        return product;
    }

}
