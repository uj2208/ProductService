package dev.ujjwal.ProductService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class CategoryDto {
    private UUID id;
    private String name;
    private List<ProductDto> products;
}
