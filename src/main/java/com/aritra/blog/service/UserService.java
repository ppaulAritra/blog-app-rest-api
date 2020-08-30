package com.aritra.blog.service;

import com.aritra.blog.common.exception.UserNotFoundException;
import com.aritra.blog.domain.User;
import com.aritra.blog.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    User readByUsername(String username) throws UserNotFoundException;

    boolean isPasswordMatches(User user, String password) throws Exception;
    UserDTO registerUser(UserDTO userDTO) throws Exception;
    UserDTO createUser(UserDTO userDTO) throws Exception;
    List<UserDTO> getUserByRoleAndApprovalStatus(Long roleId,Boolean approved);
    UserDTO approveUser(Long bloggerId);
    UserDTO deleteUser(Long bloggerId);
    UserDTO getUser(Long bloggerId);

}