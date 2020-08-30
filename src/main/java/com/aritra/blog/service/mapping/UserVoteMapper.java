package com.aritra.blog.service.mapping;

import com.aritra.blog.common.exception.AuthorizeException;
import com.aritra.blog.common.exception.EntityNotFoundException;
import com.aritra.blog.common.exception.NotFoundException;
import com.aritra.blog.domain.Blog;
import com.aritra.blog.domain.Comments;
import com.aritra.blog.domain.User;
import com.aritra.blog.domain.UserVote;
import com.aritra.blog.domain.dto.VoteDTO;
import com.aritra.blog.repository.BlogRepository;
import com.aritra.blog.repository.CommentsRepository;
import com.aritra.blog.repository.UserRepository;
import com.aritra.blog.repository.UserVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 1:53 PM
 * @project blogapp
 */
@Service
public class UserVoteMapper {
    private UserVoteRepository userVoteRepository;
    private CommentsRepository commentsRepository;
    private BlogRepository blogRepository;
    private UserRepository userRepository;

    @Autowired
    public UserVoteMapper(UserVoteRepository userVoteRepository, CommentsRepository commentsRepository, BlogRepository blogRepository, UserRepository userRepository) {
        this.userVoteRepository = userVoteRepository;
        this.commentsRepository = commentsRepository;
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }

    public UserVote requestMapper(VoteDTO voteDTO) throws NotFoundException {
        if (voteDTO.getBlogId() == null && voteDTO.getCommentId() == null) {
            throw new NotFoundException("No comment id or post id received in vote request mapper");
        }
        if (voteDTO.getBlogId() != null && userVoteRepository.existsByBlogIdAndBloggerId(voteDTO.getBlogId(), voteDTO.getBloggerId())) {
            throw new EntityExistsException("You already voted this post!");
        }

        if (voteDTO.getCommentId() != null && userVoteRepository.existsByCommentsIdAndBloggerId(voteDTO.getCommentId(), voteDTO.getBloggerId())) {
            throw new EntityExistsException("You already voted this comment!");
        }
        UserVote userVote = new UserVote();
        userVote.setVote(voteDTO.getVoteStatus());
        User blogger = userRepository.findFirstById(voteDTO.getBloggerId());
        if (blogger == null)
            throw new EntityNotFoundException("No blogger found with id " + voteDTO.getBloggerId());
        userVote.setBlogger(blogger);

        if (voteDTO.getBlogId() != null) {
            Blog blog = blogRepository.findFirstById(voteDTO.getBlogId());
            if (blog == null)
                throw new EntityNotFoundException("No blog post found with id " + voteDTO.getBlogId());
            userVote.setBlog(blog);
            if (blog.getCreatedBy().getId() == blogger.getId())
                throw new AuthorizeException("You can't vote your self");
        } else if (voteDTO.getCommentId() != null) {
            Comments comments = commentsRepository.findFirstById(voteDTO.getCommentId());
            if (comments == null)
                throw new EntityNotFoundException("No comments found with id " + voteDTO.getCommentId());
            userVote.setComments(comments);
            if (comments.getCreatedBy().getId() == blogger.getId())
                throw new AuthorizeException("You can't vote your self");
        }


        return userVote;
    }

    public VoteDTO responseMapper(UserVote entity) {
        VoteDTO voteDTO = new VoteDTO();
        voteDTO.setId(entity.getId());
        voteDTO.setVoteStatus(entity.getVote());
        return voteDTO;
    }
}
