package com.aritra.blog.controller.blogger;

import com.aritra.blog.common.Constants;
import com.aritra.blog.common.exception.NotFoundException;
import com.aritra.blog.common.util.DateTimeUtils;
import com.aritra.blog.domain.dto.BlogDTO;
import com.aritra.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 2:45 PM
 * @project blogapp
 */
@RestController
@RequestMapping("/blogger/post")
public class BloggerPostController {
    private BlogService blogService;

    @Autowired
    public BloggerPostController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("")
    public BlogDTO saveBlogPost(@RequestBody BlogDTO blogDTO) {
        return blogService.saveBlogPost(blogDTO);
    }

    @GetMapping("/{postId}")
    public BlogDTO getBlogPost(@PathVariable("postId") Long postId) {
        return blogService.getBlog(postId);
    }

    @GetMapping("/my/list")
    Page<BlogDTO> getPostsByBlogger(@RequestParam Long bloggerId, @RequestParam(value = "page", defaultValue = "0") Integer page) throws NotFoundException {
        if (bloggerId == null)
            throw new NotFoundException("Null value received");
        return blogService.getBlogListByUser(bloggerId, page);
    }

    @GetMapping("")
    public Page<BlogDTO> getBlogListByDate(@RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
                                           @RequestParam(value = "page", defaultValue = "0") Integer page) throws NotFoundException {
        if (fromDate == null)
            throw new NotFoundException("Null value received");
        return blogService.getBlogListByDate(DateTimeUtils.toDateString(fromDate), Constants.BlogType.APPROVED, page);

    }

    @DeleteMapping("/{postId}/{bloggerId}")
    public BlogDTO deleteBlogPost(@PathVariable("postId") Long postId, @PathVariable("bloggerId") Long bloggerId) {
        return blogService.deleteBlog(postId, bloggerId);
    }

}
