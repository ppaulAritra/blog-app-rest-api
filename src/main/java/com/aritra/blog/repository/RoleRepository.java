package com.aritra.blog.repository;

import com.aritra.blog.domain.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findById(long id);
    Role findFirstByName(String name);
    List<Role> findAll();

}
