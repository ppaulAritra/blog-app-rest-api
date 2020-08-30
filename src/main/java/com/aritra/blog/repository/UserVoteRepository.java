package com.aritra.blog.repository;

import com.aritra.blog.domain.UserVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 10:20 AM
 * @project blogapp
 */

public interface UserVoteRepository extends JpaRepository<UserVote,Long> {
    Boolean existsByBlogIdAndBloggerId(Long blogId,Long bloggerId);
    Boolean existsByCommentsIdAndBloggerId(Long blogId,Long bloggerId);

    @Query(value = "SELECT ((SELECT COUNT(*) FROM user_vote WHERE blog_id =:blogId AND vote = true) - (SELECT COUNT(*) FROM user_vote WHERE blog_Id =:blogId AND vote = false)) FROM user_vote", nativeQuery = true)
    Integer voteOfBlog(@Param("blogId")Long blogId);

    @Query(value = "SELECT ((SELECT COUNT(*) FROM user_vote WHERE comment_id =:commentId AND vote = true) - (SELECT COUNT(*) FROM user_vote WHERE comment_id =:commentId AND vote = false)) FROM user_vote", nativeQuery = true)
    Integer voteOfComment(@Param("commentId") Long commentId);
}
