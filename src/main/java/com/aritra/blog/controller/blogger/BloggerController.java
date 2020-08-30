package com.aritra.blog.controller.blogger;

import com.aritra.blog.common.exception.NotFoundException;
import com.aritra.blog.domain.dto.UserDTO;
import com.aritra.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.attribute.UserDefinedFileAttributeView;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 12:16 PM
 * @project blogapp
 */
@RestController
@RequestMapping("/blogger")
public class BloggerController {
    private UserService userService;

    @Autowired
    public BloggerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{bloggerId}")
    public UserDTO getBlogger(@PathVariable("bloggerId") Long bloggerId) {
        return userService.getUser(bloggerId);
    }
}
