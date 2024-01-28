package com.comprathor.service;

import com.comprathor.model.CategoryModel;
import com.comprathor.repository.entity.Category;

import java.util.List;

public interface ICategoryService {

    List<CategoryModel> getAllCategories();

    CategoryModel saveCategory(Category category);

    CategoryModel updateCategory(int id, Category updatedCategory);

    String deleteCategory(int id);

    CategoryModel getCategoryById(int id);
}
