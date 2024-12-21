package dev.charles.Auto_Shop.service;

import dev.charles.Auto_Shop.dto.ImageDTO;
import dev.charles.Auto_Shop.exception.ResourceNotFoundException;
import dev.charles.Auto_Shop.mapper.ImageMapper;
import dev.charles.Auto_Shop.model.Image;
import dev.charles.Auto_Shop.model.Product;
import dev.charles.Auto_Shop.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;
    private final ProductService productService;

    public ImageService(ImageRepository imageRepository, ImageMapper imageMapper, ProductService productService) {
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
        this.productService = productService;
    }

    public Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No image found with id: " + id));
    }

    public List<Image> getAllImage(){
        return imageRepository.findAll();
    }

    public List<ImageDTO> saveImages(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);
        List<ImageDTO> imageDTOs = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes())); // Fixed the usage of `file.getBytes()`
                image.setProduct(product);

                // Save the image to generate an ID
                Image savedImage = imageRepository.save(image);

                // Set the download URL after the image has been saved
                String buildDownloadUrl = "/api/v1/images/image/download/";
                savedImage.setDownloadUrl(buildDownloadUrl + savedImage.getId());
                imageRepository.save(savedImage);

                // Convert the saved image to DTO and add to the list
                ImageDTO imageDTO = imageMapper.toDTO(savedImage);
                imageDTOs.add(imageDTO);

            } catch (SQLException | IOException e) {
                throw new RuntimeException("Error while processing image file: " + file.getOriginalFilename(), e);
            }
        }

        return imageDTOs;
    }


    public void updateImage(MultipartFile file, Long imageId){
       Image image = getImageById(imageId);
       try{
           image.setFileName(file.getOriginalFilename());
           image.setFileName(file.getOriginalFilename());
           image.setImage(new SerialBlob(file.getBytes()));
           imageRepository.save(image);
       } catch (SQLException | IOException e) {
           throw new ResourceNotFoundException(e.getMessage());

       }

    }
}
