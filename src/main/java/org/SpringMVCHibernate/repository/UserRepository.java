package org.SpringMVCHibernate.repository;

import org.SpringMVCHibernate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
