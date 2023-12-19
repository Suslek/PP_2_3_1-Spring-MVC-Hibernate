package org.SpringMVCHibernate.controller;

import org.SpringMVCHibernate.model.User;
import org.SpringMVCHibernate.service.RoleService;
import org.SpringMVCHibernate.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "")
    public ModelAndView viewAdminPage(Principal principal) {
        ModelAndView model = new ModelAndView();
        model.setViewName("users");
        model.addObject("roles", roleService.getRoles());
        model.addObject("users", userService.getUsers());
        model.addObject("user", userService.getByUsername(principal.getName()));
        model.addObject("newUser", new User());
        return model;
    }

    @PutMapping(value = "")
    public List<User> editUser(@RequestBody User user) {
        userService.updateUser(user);
        return userService.getUsers();
    }

    @DeleteMapping(value = "/delete")
    public List<User> deleteUser(@RequestParam Long id) {
        User user = userService.getById(id);
        userService.deleteUser(user);
        return userService.getUsers();
    }

    @PostMapping(value = "")
    public List<User> createUser(@RequestBody User user) {
        System.out.println(user);
        userService.saveUser(user);
        return userService.getUsers();
    }

}
