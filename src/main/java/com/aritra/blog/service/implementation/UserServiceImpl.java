package com.aritra.blog.service.implementation;

import com.aritra.blog.common.exception.EntityNotFoundException;
import com.aritra.blog.common.exception.UserNotFoundException;
import com.aritra.blog.common.util.PasswordUtil;
import com.aritra.blog.domain.authentication.OAuthAccessToken;
import com.aritra.blog.domain.dto.UserDTO;
import com.aritra.blog.repository.OauthAccessTokenRepository;
import com.aritra.blog.repository.UserRepository;
import com.aritra.blog.domain.User;
import com.aritra.blog.service.UserService;
import com.aritra.blog.service.mapping.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private OauthAccessTokenRepository oAuthAccessTokenRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, OauthAccessTokenRepository oAuthAccessTokenRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.oAuthAccessTokenRepository = oAuthAccessTokenRepository;
    }

    @Override
    public User readByUsername(String username) throws UserNotFoundException {
        System.out.println("From username in impl:" + username);
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean isPasswordMatches(User user, String password) throws Exception {
        /**
         * Check if password matches with shaencoder, if matches encode it with bicrypt and save to the database.
         */
        if (PasswordUtil.encryptPassword(password).equals(user.getPassword())) {
            user.setPassword(PasswordUtil.encryptPassword(password));
            user = userRepository.save(user);
        }

        return PasswordUtil.getPasswordEncoder().matches(password, user.getPassword());
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) throws Exception {
        User user=userMapper.requestMapper(userDTO);
        return userMapper.responseMapper(userRepository.save(user)) ;
    }

    /**
     * Create User
     *
     * @param userDTO
     * @return userDTO
     */
    @Override
    public UserDTO createUser(UserDTO userDTO) throws Exception {
        User user=userMapper.requestMapper(userDTO);
        user.setApproved(true);
        return userMapper.responseMapper(userRepository.save(user)) ;
    }
    /**
     * Get Users
     *
     * @param roleId, approved
     * @return List
     */
    @Override
    public List<UserDTO> getUserByRoleAndApprovalStatus(Long roleId,Boolean approved) {
        List<User> userList= new ArrayList<>();
        if(approved)
         userList=userRepository.findAllByRoleIdAndIsDeletedFalseAndIsApprovedTrue(roleId);
        else
            userList=userRepository.findAllByRoleIdAndIsDeletedFalseAndIsApprovedFalse(roleId);
        return userList.stream().map(u->userMapper.responseMapper(u)).collect(Collectors.toList());
    }

    @Override
    public UserDTO approveUser(Long bloggerId) {
        User user=userRepository.findFirstById(bloggerId);
        if(user==null)
            throw new EntityNotFoundException("No blogger found with id "+bloggerId);
        user.setApproved(true);
        return userMapper.responseMapper(userRepository.save(user)) ;
    }

    @Override
    public UserDTO deleteUser(Long bloggerId) {
        User user=userRepository.findFirstById(bloggerId);
        if(user==null)
            throw new EntityNotFoundException("No blogger found with id "+bloggerId);
        user.setDeleted(true);
        OAuthAccessToken oAuthAccessToken = oAuthAccessTokenRepository.getFirstByUserName(user.getUsername());

        if (oAuthAccessToken != null)
            oAuthAccessTokenRepository.delete(oAuthAccessToken);
        return userMapper.responseMapper(userRepository.save(user)) ;
    }

    @Override
    public UserDTO getUser(Long bloggerId) {
        User user=userRepository.findFirstById(bloggerId);
        if(user==null)
            throw new EntityNotFoundException("No blogger found with id "+bloggerId);
        return userMapper.responseMapper(user);
    }
}