package org.SpringMVCHibernate.controller;

import org.SpringMVCHibernate.model.User;
import org.SpringMVCHibernate.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView editUser(@ModelAttribute("user") User user) {
        ModelAndView model = new ModelAndView();
        userService.updateUser(user);
        model.setViewName("redirect:/");
        return model;
    }

    @GetMapping(value = "/delete/")
    public ModelAndView deleteUser(@RequestParam Long id) {
        ModelAndView model = new ModelAndView();
        User user = userService.getById(id);
        userService.deleteUser(user);
        model.setViewName("redirect:/");
        return model;
    }

    @GetMapping(value = "/create")
    public String createPage(ModelMap model){
        model.addAttribute("user", new User());
        return "editPage";
    }

    @PostMapping(value = "/create")
    public ModelAndView createUser(@ModelAttribute("user") User user ) {
        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/");
        userService.createUser(user);
        return model;
    }
}
