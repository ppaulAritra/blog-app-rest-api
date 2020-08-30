package com.aritra.blog.controller.admin;

import com.aritra.blog.common.exception.NotFoundException;
import com.aritra.blog.common.util.DateTimeUtils;
import com.aritra.blog.domain.dto.BlogDTO;
import com.aritra.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 2:34 PM
 * @project blogapp
 */

@RestController
@RequestMapping("/admin/blog")
public class AdminBlogPostController {
    private BlogService blogService;

    @Autowired
    public AdminBlogPostController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("")
    public Page<BlogDTO> getBlogListByDate(@RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,

                                           @RequestParam String type, @RequestParam(value = "page", defaultValue = "0") Integer page) throws NotFoundException {
        if (fromDate == null || type == null)
            throw new NotFoundException("Null value received");
        return blogService.getBlogListByDate(DateTimeUtils.toDateString(fromDate), type, page);

    }

    @GetMapping("/approve")
    public BlogDTO approveBlog(@RequestParam Long blogId, @RequestParam Long bloggerId) throws NotFoundException {
        if (blogId == null)
            throw new NotFoundException("Null value received");
        return blogService.approveBlog(blogId, bloggerId);
    }

}
