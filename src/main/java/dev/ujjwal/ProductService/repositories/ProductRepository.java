package dev.ujjwal.ProductService.repositories;

import dev.ujjwal.ProductService.models.Category;
import dev.ujjwal.ProductService.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>{
  Optional<Product>findById(UUID uuid);

}