package org.SpringMVCHibernate.dao;

import org.SpringMVCHibernate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class UserDaoImp implements UserDao{

    private final EntityManager entityManager;

    @Autowired
    public UserDaoImp(@Qualifier("getEntityManager") EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getUsers() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public void createUser(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateUser(User user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteUser(User user) {
        entityManager.getTransaction().begin();
        entityManager.remove(user);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    @Override
    public User getById(Long id) {
        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();
        return entityManager.find(User.class, id);
    }
}
