package dev.charles.Auto_Shop.mapper;

import dev.charles.Auto_Shop.dto.ImageDTO;
import dev.charles.Auto_Shop.model.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    Image toEntity(ImageDTO imageDTO);
    ImageDTO toDTO(Image image);
}
