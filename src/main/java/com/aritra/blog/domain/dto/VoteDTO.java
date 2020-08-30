package com.aritra.blog.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 11:07 AM
 * @project blogapp
 */
@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoteDTO {
    private Long id;
    private Boolean voteStatus;
    private Long commentId;
    private Long blogId;
    private Long bloggerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getVoteStatus() {
        return voteStatus;
    }

    public void setVoteStatus(Boolean voteStatus) {
        this.voteStatus = voteStatus;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long getBloggerId() {
        return bloggerId;
    }

    public void setBloggerId(Long bloggerId) {
        this.bloggerId = bloggerId;
    }

    @Override
    public String toString() {
        return "VoteDTO{" +
                "id=" + id +
                ", voteStatus=" + voteStatus +
                ", commentId=" + commentId +
                ", blogId=" + blogId +
                ", bloggerId=" + bloggerId +
                '}';
    }
}
