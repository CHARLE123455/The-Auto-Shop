package dev.charles.Auto_Shop.dto;

import dev.charles.Auto_Shop.model.Category;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductDTO {
    private Long id;
    private String name;

    private String brand;
    private BigDecimal price;

    private int inventory;
    private String description;
    private Category category;
}
