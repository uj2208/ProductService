package dev.ujjwal.ProductService.repositories;
 public interface CustomQueries {
        String GET_ALL_PRODUCT_CATEGORY = "Select distinct c.name from product p left join category c on p.category = c.id";
        String FIND_ALL_PRODUCT = "SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.category LEFT JOIN FETCH p.price";
        String FIND_ALL_PRODUCT_BY_CATEGORY = "SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.category LEFT JOIN FETCH p.price where p.category.name=:categoryName";
        String FIND_PRODUCT_BY_ID = "SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.category LEFT JOIN FETCH p.price where p.id=:uuid";
    }

