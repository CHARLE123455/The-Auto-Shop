package dev.charles.Auto_Shop.repository;

import dev.charles.Auto_Shop.model.Category;
import dev.charles.Auto_Shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> getProductsByCategory(Category category);
    List<Product> getProductsByCategoryAndBrand(Category category, String brand);

    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByName(String name);

    List<Product> getProductsByNameAndBrand(String name, String brand);
    Long countProductsByBrandAndName(String brand, String name);

}
