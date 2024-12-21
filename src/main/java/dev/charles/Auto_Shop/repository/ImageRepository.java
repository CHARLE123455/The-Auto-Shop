package dev.charles.Auto_Shop.repository;

import dev.charles.Auto_Shop.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image  getImageByDownloadUrl(String url);
    Image getImageById(Long imageId);

}
