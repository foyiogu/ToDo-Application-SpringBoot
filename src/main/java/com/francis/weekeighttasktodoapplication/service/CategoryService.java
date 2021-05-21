package com.francis.weekeighttasktodoapplication.service;

import com.francis.weekeighttasktodoapplication.model.Category;
import com.francis.weekeighttasktodoapplication.model.Users;
import com.francis.weekeighttasktodoapplication.repository.CategoryRepository;
import com.francis.weekeighttasktodoapplication.repository.UserRepository;
import com.francis.weekeighttasktodoapplication.service.contracts.iCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements iCategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Category> findAllByUser(Long id){
       return categoryRepository.findAllByUserId(id);
    }

    @Override
    public void saveNewCategory(Category category, Long userId){
        Category categoryDB = categoryRepository.save(category);
        Optional<Users> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()){
            List<Category> categories = userOptional.get().getCategories();
            categories.add(categoryDB);
            userOptional.get().setCategories(categories);
            userRepository.save(userOptional.get());
        }
    }

    @Override
    public void deleteCategory(Long categoryId, Long userId){
        Users user = userRepository.getUsersById(userId);
        Category category = categoryRepository.getCategoryById(categoryId);
        user.getCategories().remove(category);
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public Category getCategoryById(Long id){
        return this.categoryRepository.getCategoryById(id);
    }
}
