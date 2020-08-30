package com.aritra.blog.service.mapping;

import com.aritra.blog.common.exception.EntityExistException;
import com.aritra.blog.common.exception.EntityNotFoundException;
import com.aritra.blog.common.util.PasswordUtil;
import com.aritra.blog.domain.BasicInfo;
import com.aritra.blog.domain.Role;
import com.aritra.blog.domain.User;
import com.aritra.blog.domain.dto.UserDTO;
import com.aritra.blog.repository.BasicInfoRepository;
import com.aritra.blog.repository.ImageRepository;
import com.aritra.blog.repository.UserRepository;
import com.aritra.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {


    private RoleService roleService;
    private ImageRepository imageRepository;
    private BasicInfoRepository basicInfoRepository;

    @Autowired
    public UserMapper(RoleService roleService, ImageRepository imageRepository, BasicInfoRepository basicInfoRepository) {
        this.roleService = roleService;
        this.imageRepository = imageRepository;
        this.basicInfoRepository = basicInfoRepository;
    }

    /**
     * Maps DTO to Entity
     *
     * @param dto
     * @return entity
     */
    public User requestMapper(UserDTO dto) throws Exception {
        Role role;
        if (dto.getRoleId() != null) {
            role = roleService.findRoleById(dto.getRoleId());
            if (role == null) {
                throw new EntityNotFoundException("Role does not exist");
            }
        } else
            role = roleService.findRoleByName("ROLE_BLOGGER");

        User entity = new User();

        entity.setUsername(dto.getEmail());
        entity.setPassword(PasswordUtil.encryptPassword(dto.getPassword(), PasswordUtil.EncType.BCRYPT_ENCODER, null));
        entity.setRole(role);

        BasicInfo basicInfo = new BasicInfo();
        basicInfo.setName(dto.getName());
        if (basicInfoRepository.findFirstByPhoneNo(dto.getPhone()) != null)
            throw new EntityExistException("User with this phone number already exists");
        basicInfo.setPhoneNo(dto.getPhone());
        if (basicInfoRepository.findFirstByEmail(dto.getEmail()) != null)
            throw new EntityExistException("User with this email already exists");
        basicInfo.setEmail(dto.getEmail());
        if (dto.getImageId() != null)
            basicInfo.setImage(imageRepository.findFirstById(dto.getImageId()));
        entity.setBasicInfo(basicInfo);
        return entity;
    }

    /**
     * Maps ENTITY to DTO
     *
     * @param entity
     * @return dto
     */
    public UserDTO responseMapper(User entity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(entity.getId());
        userDTO.setName(entity.getBasicInfo().getName());
        userDTO.setEmail(entity.getBasicInfo().getEmail());
        userDTO.setPhone(entity.getBasicInfo().getPhoneNo());
        userDTO.setApproved(entity.isApproved());
        userDTO.setDeleted(entity.isDeleted());
        if (entity.getBasicInfo().getImage() != null)
            userDTO.setImageUrl(entity.getBasicInfo().getImage().getUrl());
        return userDTO;
    }

}