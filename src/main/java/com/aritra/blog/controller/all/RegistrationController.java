package com.aritra.blog.controller.all;

import com.aritra.blog.domain.dto.UserDTO;
import com.aritra.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 12:00 PM
 * @project blogapp
 */
@RestController
@RequestMapping("/all/registration")
public class RegistrationController {
    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    UserDTO registerUser(@RequestBody UserDTO userDTO) throws Exception {
        return userService.registerUser(userDTO);
    }

}
