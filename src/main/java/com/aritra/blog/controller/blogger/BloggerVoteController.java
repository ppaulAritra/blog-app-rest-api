package com.aritra.blog.controller.blogger;

import com.aritra.blog.domain.UserVote;
import com.aritra.blog.domain.dto.VoteDTO;
import com.aritra.blog.service.UserVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 7:33 PM
 * @project blogapp
 */
@RestController
@RequestMapping("/blogger/vote")
public class BloggerVoteController {
    private UserVoteService userVoteService;

    @Autowired
    public BloggerVoteController(UserVoteService userVoteService) {
        this.userVoteService = userVoteService;
    }

    @PostMapping
    VoteDTO saveVote(@RequestBody VoteDTO voteDTO) throws Exception {
        return userVoteService.saveVoteForUser(voteDTO);
    }
}
