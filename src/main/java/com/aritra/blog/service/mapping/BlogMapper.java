package com.aritra.blog.service.mapping;

import com.aritra.blog.common.exception.EntityNotFoundException;
import com.aritra.blog.domain.Blog;
import com.aritra.blog.domain.Image;
import com.aritra.blog.domain.User;
import com.aritra.blog.domain.UserVote;
import com.aritra.blog.domain.dto.BlogDTO;
import com.aritra.blog.repository.ImageRepository;
import com.aritra.blog.repository.UserRepository;
import com.aritra.blog.service.CommentsService;
import com.aritra.blog.service.ImageService;
import com.aritra.blog.service.UserVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 1:09 PM
 * @project blogapp
 */
@Service
public class BlogMapper {
    private ImageRepository imageRepository;
    private CommentsService commentsService;
    private UserVoteService userVoteService;
    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public BlogMapper(ImageRepository imageRepository, CommentsService commentsService, UserVoteService userVoteService, UserRepository userRepository, UserMapper userMapper) {
        this.imageRepository = imageRepository;
        this.commentsService = commentsService;
        this.userVoteService = userVoteService;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Blog requestMapper(BlogDTO blogDTO) {
        Blog entity = new Blog();
        entity.setTitle(blogDTO.getTitle());
        entity.setGenre(blogDTO.getGenre());
        entity.setDescription(blogDTO.getDescription());
        entity.setPostDate(new Date());
        User user = userRepository.findFirstById(blogDTO.getUserId());
        if (user == null)
            throw new EntityNotFoundException("No user found with id " + blogDTO.getUserId());
        entity.setCreatedBy(user);
        if (blogDTO.getImageIds() != null) {
            List<Image> imageList = new ArrayList<>();
            for (Long i : blogDTO.getImageIds()) {
                Image image = imageRepository.findFirstById(i);
                if (image == null)
                    throw new EntityNotFoundException("No image found with id " + i);
                imageList.add(image);
            }
            entity.setImage(imageList);
        }
        return entity;
    }

    public BlogDTO responseMapper(Blog entity, Boolean withComments) {
        BlogDTO dto = new BlogDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setGenre(entity.getGenre());
        dto.setDescription(entity.getDescription());
        dto.setCreatedOn(entity.getPostDate());
        dto.setDeleted(entity.getDeleted());
        dto.setApproved(entity.getApproved());
        dto.setCreatedBy(userMapper.responseMapper(entity.getCreatedBy()));
        if (entity.getApprovedBy() != null)
            dto.setApprovedBy(userMapper.responseMapper(entity.getApprovedBy()));
        if (entity.getDeletedBy() != null)
            dto.setDeletedBy(userMapper.responseMapper(entity.getDeletedBy()));
        if (withComments) {
            dto.setComments(commentsService.getCommentsByBlog(entity.getId()));
        }
        dto.setTotalVotes(userVoteService.votesOfBlog(entity.getId()));
        return dto;
    }
}
