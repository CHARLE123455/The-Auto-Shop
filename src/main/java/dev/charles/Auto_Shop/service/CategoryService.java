package dev.charles.Auto_Shop.service;

import dev.charles.Auto_Shop.dto.CategoryDTO;
import dev.charles.Auto_Shop.mapper.CategoryMapper;
import dev.charles.Auto_Shop.model.Category;
import dev.charles.Auto_Shop.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category categoryEntity = categoryMapper.toEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(categoryEntity);
        return categoryMapper.toDTO(savedCategory);
    }


    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Category category, Long Id) {
        categoryRepository.delete(category);
    }
    public void deleteCategoryById(Category category, Long id) {
        categoryRepository.deleteById(id);
    }
    public Category getCategoryById(Category category, Long id) {
        return categoryRepository.findById(id).get();
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.getCategoryByName(name);
    }

}
