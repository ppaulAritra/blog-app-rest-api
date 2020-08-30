package com.aritra.blog.repository;

import com.aritra.blog.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
    User findFirstById(Long userId);

    List<User> findAllByRoleIdAndIsDeletedFalseAndIsApprovedTrue(Long roleId);

   List<User> findAllByRoleIdAndIsDeletedFalseAndIsApprovedFalse(Long roleId);

}