package com.aritra.blog.service.implementation;

import com.aritra.blog.domain.Role;
import com.aritra.blog.repository.RoleRepository;
import com.aritra.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findRoleById(long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role findRoleByName(String roleName) {
        return roleRepository.findFirstByName(roleName);
    }
}