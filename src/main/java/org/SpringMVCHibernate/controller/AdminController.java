package org.SpringMVCHibernate.controller;

import org.SpringMVCHibernate.model.User;
import org.SpringMVCHibernate.service.RoleService;
import org.SpringMVCHibernate.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

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
    public ResponseEntity<?> editUser(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deleteUser(@RequestParam Long id) {
        User user = userService.getById(id);
        userService.deleteUser(user);
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        System.out.println(user);
        userService.saveUser(user);
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

}
