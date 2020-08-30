package com.aritra.blog.service;

import com.aritra.blog.common.util.PasswordUtil;
import com.aritra.blog.domain.BasicInfo;
import com.aritra.blog.domain.User;
import com.aritra.blog.repository.RoleRepository;
import com.aritra.blog.domain.Role;
import com.aritra.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Autowired
    public DataLoader(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // Load User Roles
        if (roleRepository.findById(1) == null)
            roleRepository.save(new Role(1,"ROLE_ADMIN"));
        else if (!roleRepository.findById(1).getName().equals("ROLE_ADMIN")) {
            Role oldAdminDate = roleRepository.findById(1);
            Role role = new Role();
            role.setId(oldAdminDate.getId());
            role.setName("ROLE_ADMIN");
           Role admin= roleRepository.save(role);
        }

        if (roleRepository.findById(2) == null)
            roleRepository.save(new Role(2,"ROLE_BLOGGER"));
        else if (!roleRepository.findById(2).getName().equals("ROLE_BLOGGER")) {
            Role oldAdminDate = roleRepository.findById(2);
            Role role = new Role();
            role.setId(oldAdminDate.getId());
            role.setName("ROLE_EMPLOYEE");
           roleRepository.save(role);
        }


        User user= new User();
        user.setUsername("admin@gmail.com");
        user.setPassword(PasswordUtil.encryptPassword("00000000", PasswordUtil.EncType.BCRYPT_ENCODER, null));
        user.setRole(roleRepository.findById(1));
        user.setApproved(true);
        BasicInfo basicInfo= new BasicInfo();
        basicInfo.setEmail("admin@gmail.com");
        basicInfo.setPhoneNo("01789000001");
        basicInfo.setName("Aritra Paul");
        user.setBasicInfo(basicInfo);
        userRepository.save(user);

    }
}