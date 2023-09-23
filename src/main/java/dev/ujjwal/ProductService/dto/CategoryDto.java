package dev.ujjwal.ProductService.dto;

import dev.ujjwal.ProductService.models.Product;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class CategoryDto {
    private UUID id;
    private String name;
    private List<ProductDto> products;
}
