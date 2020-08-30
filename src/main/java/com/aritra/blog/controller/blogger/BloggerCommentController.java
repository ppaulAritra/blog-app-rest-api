package com.aritra.blog.controller.blogger;

import com.aritra.blog.common.exception.NotFoundException;
import com.aritra.blog.domain.dto.CommentDTO;
import com.aritra.blog.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 6:25 PM
 * @project blogapp
 */
@RestController
@RequestMapping("/blogger/comment")
public class BloggerCommentController {
    CommentsService commentsService;

    @Autowired
    public BloggerCommentController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @PostMapping
    public CommentDTO saveComment(@RequestBody CommentDTO dto) throws NotFoundException {
        return commentsService.saveComment(dto);
    }

    @GetMapping
    public List<CommentDTO> getCommentsByBlog(@RequestParam Long blogId) throws NotFoundException {
        if (blogId == null)
            throw new NotFoundException("Null blogId received ");
        return commentsService.getCommentsByBlog(blogId);
    }

    @DeleteMapping("/{commentId}/{bloggerId}")
    public CommentDTO deleteComment(@PathVariable("commentId") Long commentId, @PathVariable("bloggerId") Long bloggerId) throws NotFoundException {
        if (bloggerId == null)
            throw new NotFoundException("Null bloggerId received ");
        return commentsService.deleteComment(commentId, bloggerId);
    }
}
