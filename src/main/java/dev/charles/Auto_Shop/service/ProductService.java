package dev.charles.Auto_Shop.service;

import dev.charles.Auto_Shop.dto.ProductDTO;
import dev.charles.Auto_Shop.mapper.ProductMapper;
import dev.charles.Auto_Shop.model.Category;
import dev.charles.Auto_Shop.model.Product;
import dev.charles.Auto_Shop.repository.ProductRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public Product createProduct(ProductDTO productDTO, Category category) {
        Product product = new Product();
                product.setName(productDTO.getName());
                product.setCategory(category);
                product.setPrice(productDTO.getPrice());
                product.setBrand(productDTO.getBrand());
                product.setDescription(productDTO.getDescription());
                product.setInventory(productDTO.getInventory());
                return productRepository.save(product);

    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product =productMapper.toEntity(productDTO);
        return productMapper.toDTO(productRepository.save(product));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Product product) {
        var productIdExists = productRepository.existsById(product.getId());
        if (!productIdExists) {
            throw new ServiceException("Product Id not found");
        }
        productRepository.delete(product);

    }

    public Product getProductById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid product ID");
        }

        return productRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Product not found"));
    }

    public List<Product> getProductsByCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }

        List<Product> products = productRepository.getProductsByCategory(category);
        if (products.isEmpty()) {
            throw new ServiceException("No products found for the specified category");
        }

        return products;
    }

    public List<Product> getProductsByName(String name) {
        return productRepository.getProductsByName(name);
    }

    public List<Product> getProductsByCategory(Category category, Pageable pageable) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        return productRepository.getProductsByCategory(category);
    }

    public List<Product> getProductByCategoryAndBrand(Category category, String brand) {
        return productRepository.getProductsByCategoryAndBrand(category, brand);
    }
    public List<Product> getProductByBrand(String brand) {
        return productRepository.getProductsByBrand(brand);
    }
    public List<Product> getProductsByNameAndBrand(String name, String brand) {
        return productRepository.getProductsByNameAndBrand(name, brand);
    }
    public Long countProductByBrandAndName(String brand, String name) {
        return productRepository.countProductsByBrandAndName(brand, name);
    }


}
