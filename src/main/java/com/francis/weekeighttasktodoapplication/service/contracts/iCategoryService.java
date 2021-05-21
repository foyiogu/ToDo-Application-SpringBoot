package com.francis.weekeighttasktodoapplication.service.contracts;

import com.francis.weekeighttasktodoapplication.model.Category;

import java.util.List;

public interface iCategoryService {
    List<Category> findAllByUser(Long id);
    void saveNewCategory(Category category, Long userId);
    void deleteCategory(Long categoryId, Long userId);
    Category getCategoryById(Long id);
}
