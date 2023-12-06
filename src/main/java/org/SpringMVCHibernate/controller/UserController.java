package org.SpringMVCHibernate.controller;

import org.SpringMVCHibernate.model.User;
import org.SpringMVCHibernate.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String displayUsers(ModelMap model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping(value = "/edit/")
    public String editPage(ModelMap model, @RequestParam Long id){
        model.addAttribute("user", userService.getById(id));
        return "editPage";
    }

    @PostMapping (value = "/edit")
    public String editUser(User user) {
        userService.saveUser(user);
        return ("redirect:/");
    }

    @GetMapping(value = "/delete/")
    public String deleteUser(@RequestParam Long id) {
        User user = userService.getById(id);
        userService.deleteUser(user);
        return ("redirect:/");
    }

    @GetMapping(value = "/create")
    public String createPage(User user){
        return "editPage";
    }

    @PostMapping(value = "/create")
    public String createUser(User user ) {
        userService.saveUser(user);
        return ("redirect:/");
    }
}
