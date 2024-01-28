package com.comprathor.service.impl;

import com.comprathor.model.CategoryModel;
import com.comprathor.repository.CategoryRepo;
import com.comprathor.repository.entity.Category;
import com.comprathor.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<CategoryModel> getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        return categories.stream()
                .map(this::convertToCategoryModel)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryModel saveCategory(Category category) {
        Category savedCategory = categoryRepo.save(category);
        return convertToCategoryModel(savedCategory);
    }

    @Override
    public CategoryModel updateCategory(int id, Category updatedCategory) {
        Optional<Category> existingCategoryOptional = categoryRepo.findById(id);

        if (existingCategoryOptional.isPresent()) {
            Category existingCategory = existingCategoryOptional.get();
            existingCategory.setName(updatedCategory.getName());

            categoryRepo.save(existingCategory);
            return convertToCategoryModel(existingCategory);
        } else {
            throw new RuntimeException("Error, categoría no encontrada");
        }
    }

    @Override
    public String deleteCategory(int id) {
        if (categoryRepo.existsById(id)) {
            categoryRepo.deleteById(id);
            return "Categoría eliminada";
        } else {
            throw new RuntimeException("Error, categoría no encontrada");
        }
    }

    @Override
    public CategoryModel getCategoryById(int id) {
        Optional<Category> categoryOptional = categoryRepo.findById(id);

        if (categoryOptional.isPresent()) {
            return convertToCategoryModel(categoryOptional.get());
        } else {
            throw new RuntimeException("Error, categoría no encontrada");
        }
    }

    private CategoryModel convertToCategoryModel(Category category) {
        return CategoryModel.builder()
                .id_category(category.getId_category())
                .name(category.getName())
                .build();
    }
}
