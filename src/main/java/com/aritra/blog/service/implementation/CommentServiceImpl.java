package com.aritra.blog.service.implementation;

import com.aritra.blog.common.exception.AuthorizeException;
import com.aritra.blog.common.exception.EntityNotFoundException;
import com.aritra.blog.common.exception.NotFoundException;
import com.aritra.blog.domain.Comments;
import com.aritra.blog.domain.User;
import com.aritra.blog.domain.dto.CommentDTO;
import com.aritra.blog.repository.CommentsRepository;
import com.aritra.blog.repository.UserRepository;
import com.aritra.blog.service.CommentsService;
import com.aritra.blog.service.mapping.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 10:27 AM
 * @project blogapp
 */
@Service
public class CommentServiceImpl implements CommentsService {
    private CommentsRepository commentsRepository;
    private CommentMapper commentMapper;
    private UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentsRepository commentsRepository, CommentMapper commentMapper, UserRepository userRepository) {
        this.commentsRepository = commentsRepository;
        this.commentMapper = commentMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<CommentDTO> getCommentsByBlog(Long blogId) {
        List<Comments> commentList = commentsRepository.findAllByBlogIdAndIsDeletedFalse(blogId);
        return commentList.stream().map(comments -> commentMapper.responseMapper(comments)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO saveComment(CommentDTO commentDTO) throws NotFoundException {
        Comments comment = commentMapper.requestMapper(commentDTO);
        return commentMapper.responseMapper(commentsRepository.save(comment));
    }

    @Override
    public CommentDTO deleteComment(Long commentId, Long bloggerId) {
        Comments comment=commentsRepository.findFirstById(commentId);
        if(commentId== null)
            throw new EntityNotFoundException("No comment found with id "+commentId);
        User user =userRepository.findFirstById(bloggerId);
        if(user== null)
            throw new EntityNotFoundException("No user found with id "+bloggerId);
        if(user.getRole().getName().equals("ROLE_ADMIN")){
            comment.setDeleted(true);
        }
        else{
            if(bloggerId==comment.getCreatedBy().getId())
                comment.setDeleted(true);
            else
                throw new AuthorizeException("You can't delete this comment");
        }
        comment.setDeletedBy(user);
        return commentMapper.responseMapper(commentsRepository.save(comment));
    }
}
