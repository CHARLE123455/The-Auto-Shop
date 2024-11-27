package dev.charles.Auto_Shop.dto;

import dev.charles.Auto_Shop.model.Product;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private List<Product> products;
}
