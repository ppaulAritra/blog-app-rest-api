package com.aritra.blog.repository;

import com.aritra.blog.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 10:19 AM
 * @project blogapp
 */

public interface CommentsRepository extends JpaRepository<Comments,Long> {
    Comments findFirstById(Long id);
    List<Comments> findAllByBlogIdAndIsDeletedFalse(Long blogId);
}
