package dev.ujjwal.ProductService.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Category  extends BaseModel{
    @Column
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
    // this is the same relation being mapped by category attribute in the other (Product) class

}
