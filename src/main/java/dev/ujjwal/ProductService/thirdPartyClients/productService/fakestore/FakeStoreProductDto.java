package dev.ujjwal.ProductService.thirdPartyClients.productService.fakestore;

import dev.ujjwal.ProductService.models.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
