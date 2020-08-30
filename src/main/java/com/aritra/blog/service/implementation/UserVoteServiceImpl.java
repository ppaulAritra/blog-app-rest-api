package com.aritra.blog.service.implementation;

import com.aritra.blog.domain.UserVote;
import com.aritra.blog.domain.dto.VoteDTO;
import com.aritra.blog.repository.UserVoteRepository;
import com.aritra.blog.service.UserVoteService;
import com.aritra.blog.service.mapping.UserVoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 10:28 AM
 * @project blogapp
 */
@Service
public class UserVoteServiceImpl implements UserVoteService {

    private UserVoteRepository userVoteRepository;
    private UserVoteMapper userVoteMapper;

    @Autowired
    public UserVoteServiceImpl(UserVoteRepository userVoteRepository, UserVoteMapper userVoteMapper) {
        this.userVoteRepository = userVoteRepository;
        this.userVoteMapper = userVoteMapper;
    }

    @Override
    public VoteDTO saveVoteForUser(VoteDTO voteDTO) throws Exception {
        UserVote userVote=userVoteMapper.requestMapper(voteDTO);
        return userVoteMapper.responseMapper(userVoteRepository.save(userVote));
    }

    @Override
    public Integer votesOfBlog(Long blogId) {
        return userVoteRepository.voteOfBlog(blogId);
    }

    @Override
    public Integer votesOfComment(Long commentId) {
        return userVoteRepository.voteOfComment(commentId);
    }
}
