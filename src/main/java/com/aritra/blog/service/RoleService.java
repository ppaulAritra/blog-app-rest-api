package com.aritra.blog.service;

import com.aritra.blog.domain.Role;

public interface RoleService {

    Role findRoleById(long id);

    Role findRoleByName(String roleName);


}
