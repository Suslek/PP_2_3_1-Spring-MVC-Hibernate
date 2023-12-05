package org.SpringMVCHibernate.dao;

import org.SpringMVCHibernate.model.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    User getById(Long id);
}
