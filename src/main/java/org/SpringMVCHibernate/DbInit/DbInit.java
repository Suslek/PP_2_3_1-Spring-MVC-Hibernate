package org.SpringMVCHibernate.DbInit;

import jakarta.annotation.PostConstruct;
import org.SpringMVCHibernate.model.Role;
import org.SpringMVCHibernate.model.User;
import org.SpringMVCHibernate.repository.RoleRepository;
import org.SpringMVCHibernate.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class DbInit {
    private final UserService userService;

    private final RoleRepository roleRepository;

    public DbInit(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    private void postConstruct() {
        User admin = new User("admin", "admin", "admin", "admin", "admin");
        User normalUser = new User("user", "user", "user", "user", "user");
        Set<Role> set = new HashSet<>(roleRepository.findAll());

        normalUser.setRoles(Collections.singleton(new Role(1L, "USER")));
        admin.setRoles(set);

        try {
            userService.saveUser(admin);
            userService.saveUser(normalUser);
        } catch (Exception ignored) {

        }


    }
}
