package com.aritra.blog.controller.admin;

import com.aritra.blog.common.exception.AuthorizeException;
import com.aritra.blog.common.exception.NotFoundException;
import com.aritra.blog.domain.Role;
import com.aritra.blog.domain.User;
import com.aritra.blog.domain.dto.UserDTO;
import com.aritra.blog.repository.RoleRepository;
import com.aritra.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 12:04 PM
 * @project blogapp
 */
@RestController
@RequestMapping("/admin/user")
public class AdminUserController {
    private UserService userService;
    private RoleRepository roleRepository;

    @Autowired
    public AdminUserController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostMapping("")
    UserDTO registerUser(@RequestBody UserDTO userDTO) throws Exception {
        return userService.createUser(userDTO);
    }

    @GetMapping("")
    public List<UserDTO> getUserListByRoleAndApprovalStatus(@RequestParam Long roleId, @RequestParam Boolean approvalStatus) throws NotFoundException {
        if (roleId == null || approvalStatus == null)
            throw new NotFoundException("Null value received");
        return userService.getUserByRoleAndApprovalStatus(roleId, approvalStatus);

    }

    @GetMapping("/approve")
    public UserDTO approveUser(@RequestParam Long bloggerId) throws NotFoundException {
        if (bloggerId == null)
            throw new NotFoundException("Null value received");
        return userService.approveUser(bloggerId);
    }

    @DeleteMapping("/{bloggerId}/{decisionById}")
    public UserDTO deactivateUser(@PathVariable("bloggerId") Long bloggerId, @PathVariable("decisionById") Long decisionById) throws NotFoundException {
        if (bloggerId == decisionById)
            throw new AuthorizeException("You can't delete yourself");
        return userService.deleteUser(bloggerId);
    }

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}
