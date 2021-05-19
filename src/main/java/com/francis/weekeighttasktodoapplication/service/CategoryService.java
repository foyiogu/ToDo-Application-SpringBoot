package com.francis.weekeighttasktodoapplication.service;

import com.francis.weekeighttasktodoapplication.model.Category;
import com.francis.weekeighttasktodoapplication.model.Users;
import com.francis.weekeighttasktodoapplication.repository.CategoryRepository;
import com.francis.weekeighttasktodoapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    public List<Category> findAllByUser(Long id){
       return categoryRepository.findAllByUserId(id);
    }

    public void saveNewCategory(Category category, Long userId){
        Category categoryDB = categoryRepository.save(category);
        Optional<Users> userOptional = userRepository.findById(userId);
        List<Category> categories = userOptional.get().getCategories();
        categories.add(categoryDB);
        userOptional.get().setCategories(categories);
        userRepository.save(userOptional.get());
    }


}
