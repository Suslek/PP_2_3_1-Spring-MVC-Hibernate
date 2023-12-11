package org.SpringMVCHibernate.service;

import org.SpringMVCHibernate.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getUsers();

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    User getById(Long id);

    User getByUsername(String username);
}
