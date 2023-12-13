package org.SpringMVCHibernate.controller;

import org.SpringMVCHibernate.model.User;
import org.SpringMVCHibernate.service.RoleService;
import org.SpringMVCHibernate.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/admin")
    public String viewAdminPage(ModelMap model, Principal principal, User newUser) {
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("user", userService.getByUsername(principal.getName()));
        model.addAttribute("newUser", new User());
        return "users";
    }

    @PostMapping(value = "/admin/edit")
    public String editUser(User user) {
        userService.updateUser(user);
        return ("redirect:/admin");
    }

    @PostMapping(value = "/admin/delete/")
    public String deleteUser(User user) {
        userService.deleteUser(user);
        return ("redirect:/admin");
    }

    @PostMapping(value = "/admin/create")
    public String createUser(User user) {
        userService.saveUser(user);
        return ("redirect:/admin");
    }

}
