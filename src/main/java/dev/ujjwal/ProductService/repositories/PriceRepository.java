package dev.ujjwal.ProductService.repositories;

import dev.ujjwal.ProductService.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PriceRepository extends JpaRepository<Price, UUID> {

}
