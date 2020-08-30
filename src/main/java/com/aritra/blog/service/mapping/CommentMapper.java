package com.aritra.blog.service.mapping;

import com.aritra.blog.common.exception.EntityNotFoundException;
import com.aritra.blog.common.exception.NotFoundException;
import com.aritra.blog.domain.Blog;
import com.aritra.blog.domain.Comments;
import com.aritra.blog.domain.User;
import com.aritra.blog.domain.dto.CommentDTO;
import com.aritra.blog.repository.BlogRepository;
import com.aritra.blog.repository.UserRepository;
import com.aritra.blog.service.UserVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 1:32 PM
 * @project blogapp
 */
@Service
public class CommentMapper {
    private BlogRepository blogRepository;
    private UserRepository userRepository;
    private UserMapper userMapper;
    private UserVoteService userVoteService;

    @Autowired
    public CommentMapper(BlogRepository blogRepository, UserRepository userRepository, UserMapper userMapper, UserVoteService userVoteService) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userVoteService = userVoteService;
    }

    public Comments requestMapper(CommentDTO dto) throws NotFoundException {
        Comments comments = new Comments();
        if (dto.getCreatedById() == null || dto.getBlogId() == null)
            throw new NotFoundException("Null value received in comment dto");
        comments.setComment(dto.getCommentBody());
        comments.setCommentDate(new Date());
        User user = userRepository.findFirstById(dto.getCreatedById());
        if (user == null)
            throw new EntityNotFoundException("No user found with id " + dto.getCreatedById());
        comments.setCreatedBy(user);
        Blog blog = blogRepository.findFirstById(dto.getBlogId());
        if (blog == null)
            throw new EntityNotFoundException("No blog found with id " + dto.getBlogId());
        comments.setBlog(blog);
        return comments;
    }

    public CommentDTO responseMapper(Comments entity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentId(entity.getId());
        commentDTO.setBlogId(entity.getBlog().getId());
        commentDTO.setCommentDate(entity.getCommentDate());
        commentDTO.setCommentBody(entity.getComment());
        commentDTO.setDeleted(entity.getDeleted());
        commentDTO.setCreatedBy(userMapper.responseMapper(entity.getCreatedBy()));
        if (entity.getDeletedBy() != null)
            commentDTO.setDeletedBy(userMapper.responseMapper(entity.getDeletedBy()));
        commentDTO.setTotalVotes(userVoteService.votesOfComment(entity.getId()));
        return commentDTO;
    }
}
