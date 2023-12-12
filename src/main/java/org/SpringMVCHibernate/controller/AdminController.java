package org.SpringMVCHibernate.controller;

import org.SpringMVCHibernate.model.User;
import org.SpringMVCHibernate.service.RoleService;
import org.SpringMVCHibernate.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/admin")
    public String viewAdminPage(ModelMap model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping(value = "/admin/edit/")
    public String viewEditPage(ModelMap model, @RequestParam Long id) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("roles", roleService.getRoles());
        return "editPage";
    }

    @PostMapping(value = "/admin/edit")
    public String editUser(User user) {
        userService.updateUser(user);
        return ("redirect:/admin");
    }

    @GetMapping(value = "/admin/delete/")
    public String deleteUser(@RequestParam Long id) {
        User user = userService.getById(id);
        userService.deleteUser(user);
        return ("redirect:/admin");
    }

    @GetMapping(value = "/admin/create")
    public String viewCreatePage(ModelMap model,User user) {
        model.addAttribute("roles", roleService.getRoles());
        return "editPage";
    }

    @PostMapping(value = "/admin/create")
    public String createUser(User user) {
        userService.saveUser(user);
        return ("redirect:/admin");
    }

}
