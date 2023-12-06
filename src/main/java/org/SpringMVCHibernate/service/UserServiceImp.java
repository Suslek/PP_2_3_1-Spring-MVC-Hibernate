package org.SpringMVCHibernate.service;

import org.SpringMVCHibernate.model.User;
import org.SpringMVCHibernate.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getById(Long id) {
        return userRepository.getReferenceById(id);
    }


}
