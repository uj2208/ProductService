package dev.ujjwal.ProductService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ProductDto {
    private UUID id;
    private String title;
    private String description;
    private String image;
    private CategoryDto category;
    private String currency;
    private double price;
}
