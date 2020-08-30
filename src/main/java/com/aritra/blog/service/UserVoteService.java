package com.aritra.blog.service;

import com.aritra.blog.domain.UserVote;
import com.aritra.blog.domain.dto.VoteDTO;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 10:26 AM
 * @project blogapp
 */

public interface UserVoteService {
    VoteDTO saveVoteForUser(VoteDTO voteDTOo) throws Exception;
    Integer votesOfBlog(Long blogId);
    Integer votesOfComment(Long commentId);


}
