package dev.charles.Auto_Shop.mapper;

import dev.charles.Auto_Shop.dto.ProductDTO;
import dev.charles.Auto_Shop.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductDTO productDTO);
    ProductDTO toDTO(Product product);


}
