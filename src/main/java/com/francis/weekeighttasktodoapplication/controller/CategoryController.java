package com.francis.weekeighttasktodoapplication.controller;

import com.francis.weekeighttasktodoapplication.model.Category;
import com.francis.weekeighttasktodoapplication.model.Users;
import com.francis.weekeighttasktodoapplication.service.CategoryService;
import com.francis.weekeighttasktodoapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
public class CategoryController {

    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(UserService userService, CategoryService categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }


    @GetMapping("/category/{userId}")
    public String getCategoryById(@PathVariable("userId") Long userId, Model model, HttpSession session){
        Users user = (Users) session.getAttribute("user_session");
        model.addAttribute("user", user);
        model.addAttribute("newCategory", new Category());
        model.addAttribute("allCategories", categoryService.findAllByUser(userId));
        return "category";
    }

    @PostMapping("/post/{id}")
    public String addNewCategory(@PathVariable("id") Long id, String categoryName){
        Category category = new Category();
        Users another = userService.getUserById(id);
        category.setCategoryName(categoryName);
        category.setCreated_at(LocalDateTime.now());
        category.setUser(another);
        categoryService.saveNewCategory(category, another.getId());
        return "redirect:/category/" + another.getId();
    }

    @RequestMapping("/delete/{id}/{userID}")
    public String deleteCategory(@PathVariable(name = "id")Long id,@PathVariable(name = "userID")Long userId){
        Users user = userService.getUserById(userId);
        categoryService.deleteCategory(id, userId);
        return "redirect:/category/" + user.getId();
    }


}
