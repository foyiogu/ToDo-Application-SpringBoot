package com.francis.weekeighttasktodoapplication.controller;

import com.francis.weekeighttasktodoapplication.model.Users;
import com.francis.weekeighttasktodoapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new Users());
        return "index";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") Users user, HttpServletRequest request, Model model){

        //For Validation of new user
        Users validUser = userService.getUser(user.getEmail(),user.getPassword());

        if (validUser == null) {
            model.addAttribute("not_found", "Email or password not valid, please check again or sign up");
            return "index";
        }else{
            request.getSession().invalidate();
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(1000);
            session.setAttribute("user_session",validUser);
            return "redirect:/category/" + validUser.getId();
        }
    }

    //FOR SIGNING UP
    @GetMapping("/signup")
    public String showSignUpPage(Model model){
        model.addAttribute("user", new Users());
        return "signup";
    }

    @PostMapping("/signupPost")
    public String reg(@ModelAttribute("user") @Valid Users user, Model model, BindingResult bindingResult, String email, String password){
        Users existingUser = userService.getUser(email,password);
        if (existingUser != null){
            model.addAttribute("existingUser", "user already exists");
            return "signup";
        }

        if (bindingResult.hasErrors()){
            return "signup";
        }
            userService.registerNewUser(user);
            return "index";
    }

    //FOR LOG OUT

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
