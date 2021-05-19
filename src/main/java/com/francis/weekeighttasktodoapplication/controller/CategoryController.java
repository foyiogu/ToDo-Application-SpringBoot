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

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/{userId}")
    public String getCategoryById(@PathVariable("userId") Long userId, Model model, HttpSession session){
        Users user = (Users) session.getAttribute("user_session");
//        Users users = userService.getUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("newCategory", new Category());
        model.addAttribute("allCategories", categoryService.findAllByUser(userId));
        return "category";
    }

    @PostMapping("/post/{id}")
    public String addNewCategory(@PathVariable("id") Long id, HttpSession session, String categoryName){
        Category category = new Category();

//        Users userObj = (Users) session.getAttribute("user_session");
        Users another = userService.getUserById(id);
//        if (userObj==null && userObj!=another){
//            return "redirect:/index";
//        }
        category.setCategoryName(categoryName);
        category.setCreated_at(LocalDateTime.now());
        category.setUser(another);
        categoryService.saveNewCategory(category, another.getId());
        return "redirect:/category/" + another.getId();
    }

}
