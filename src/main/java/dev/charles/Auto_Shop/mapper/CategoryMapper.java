package dev.charles.Auto_Shop.mapper;

import dev.charles.Auto_Shop.dto.CategoryDTO;
import dev.charles.Auto_Shop.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryDTO categoryDTO);
    CategoryDTO toDTO(Category category);
}
