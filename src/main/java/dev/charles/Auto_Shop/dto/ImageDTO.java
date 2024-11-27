package dev.charles.Auto_Shop.dto;

import dev.charles.Auto_Shop.model.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.sql.Blob;

@Data
public class ImageDTO {
    private Long id;
    private String fileName;

    private String filePath;
    private String fileType;

    @Lob
    private Blob image;
    private String downloadUrl;

    private Product product;
}
