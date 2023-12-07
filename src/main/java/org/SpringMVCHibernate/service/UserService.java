package org.SpringMVCHibernate.service;

import org.SpringMVCHibernate.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    User getById(Long id);
}
