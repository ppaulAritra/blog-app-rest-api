package com.aritra.blog.service;

import com.aritra.blog.common.exception.NotFoundException;
import com.aritra.blog.domain.dto.BlogDTO;
import org.springframework.data.domain.Page;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 10:24 AM
 * @project blogapp
 */

public interface BlogService {

    BlogDTO saveBlogPost(BlogDTO blog);

    BlogDTO getBlog(Long blogId);

    Page<BlogDTO> getBlogListByDate(String fromDate, String type, Integer page) throws NotFoundException;

    Page<BlogDTO> getBlogListByUser(Long bloggerId, Integer page);

    BlogDTO deleteBlog(Long blogId, Long bloggerId);

    BlogDTO approveBlog(Long blogId, Long bloggerId);
}
