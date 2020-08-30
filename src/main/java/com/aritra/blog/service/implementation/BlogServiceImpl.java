package com.aritra.blog.service.implementation;

import com.aritra.blog.common.Constants;
import com.aritra.blog.common.PageAttr;
import com.aritra.blog.common.exception.AuthorizeException;
import com.aritra.blog.common.exception.EntityNotFoundException;
import com.aritra.blog.common.exception.NotFoundException;
import com.aritra.blog.domain.Blog;
import com.aritra.blog.domain.User;
import com.aritra.blog.domain.dto.BlogDTO;
import com.aritra.blog.repository.BlogRepository;
import com.aritra.blog.repository.UserRepository;
import com.aritra.blog.service.BlogService;
import com.aritra.blog.service.mapping.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 10:26 AM
 * @project blogapp
 */
@Service
public class BlogServiceImpl implements BlogService {
    private BlogMapper blogMapper;
    private BlogRepository blogRepository;
    private UserRepository userRepository;
    @Autowired
    public BlogServiceImpl(BlogMapper blogMapper, BlogRepository blogRepository, UserRepository userRepository) {
        this.blogMapper = blogMapper;
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }




    @Override
    public BlogDTO saveBlogPost(BlogDTO blogDTO) {
        Blog blog= blogMapper.requestMapper(blogDTO);
        return blogMapper.responseMapper(blogRepository.save(blog),false);
    }

    @Override
    public BlogDTO getBlog(Long blogId) {
        Blog blog= blogRepository.findFirstById(blogId);
        if(blog== null)
            throw new EntityNotFoundException("No blog post found with id "+blogId);
        return blogMapper.responseMapper(blog,true);
    }

    @Override
    public Page<BlogDTO> getBlogListByDate(String fromDate, String type, Integer page) throws NotFoundException {
        Page<Blog> blogList ;
        if(Constants.BlogType.APPROVED.equals(type)) {
            blogList = blogRepository.findAllApprovedBlogPostInBetweenDate(fromDate,  new PageRequest(page, PageAttr.PAGE_SIZE));
       }
       else if(Constants.BlogType.NOT_APPROVED.equals(type)){
            blogList = blogRepository.findAllNotApprovedBlogPostInBetweenDate(fromDate, new PageRequest(page, PageAttr.PAGE_SIZE));
       }
       else
           throw new NotFoundException("Not a valid blog type");
        return blogList.map(blog -> blogMapper.responseMapper(blog,false));
    }

    @Override
    public Page<BlogDTO> getBlogListByUser(Long bloggerId, Integer page) {
        Page<Blog> blogList=blogRepository.findAllBlogPostOfUser(bloggerId,new PageRequest(page, PageAttr.PAGE_SIZE));
        return blogList.map(blog -> blogMapper.responseMapper(blog,false));
    }


    @Override
    public BlogDTO deleteBlog(Long blogId,Long bloggerId) {
        Blog blog= blogRepository.findFirstById(blogId);
        if(blog== null)
            throw new EntityNotFoundException("No blog post found with id "+blogId);
        User user =userRepository.findFirstById(bloggerId);
        if(user== null)
            throw new EntityNotFoundException("No user found with id "+bloggerId);
        if(user.getRole().getName().equals("ROLE_ADMIN")){
        blog.setDeleted(true);}
        else
        {
            if(bloggerId==blog.getCreatedBy().getId())
                blog.setDeleted(true);
            else
                throw new AuthorizeException("You can't delete this post");
        }
        blog.setDeletedBy(user);
        return blogMapper.responseMapper(blogRepository.save(blog),false);
    }

    @Override
    public BlogDTO approveBlog(Long blogId,Long bloggerId) {
        Blog blog= blogRepository.findFirstById(blogId);
        if(blog== null)
            throw new EntityNotFoundException("No blog post found with id "+blogId);
        User user =userRepository.findFirstById(bloggerId);
        if(user== null)
            throw new EntityNotFoundException("No user found with id "+bloggerId);
        blog.setApproved(true);
        blog.setApprovedBy(user);
        return blogMapper.responseMapper(blogRepository.save(blog),false);
    }
}
