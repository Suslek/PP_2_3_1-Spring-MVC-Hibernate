package org.SpringMVCHibernate.controller;

import org.SpringMVCHibernate.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping (value = "/logout")
    public String logout() {
        return "redirect:/";
    }

    @GetMapping(value = "/")
    public String homePage() {
        return "homePage";
    }

    @GetMapping(value = "/user")
    public String displayUserInfo(ModelMap model, Principal principal) {
        model.addAttribute("user", userService.getByUsername(principal.getName()));
        return "user";
    }


}
