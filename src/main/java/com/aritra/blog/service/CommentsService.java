package com.aritra.blog.service;

import com.aritra.blog.common.exception.NotFoundException;
import com.aritra.blog.domain.dto.CommentDTO;

import java.util.List;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 10:24 AM
 * @project blogapp
 */

public interface CommentsService {
    List<CommentDTO> getCommentsByBlog(Long blogId);

    CommentDTO saveComment(CommentDTO commentDTO) throws NotFoundException;

    CommentDTO deleteComment(Long commentId, Long userId);
}
