package com.aritra.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 9:56 AM
 * @project blogapp
 */
@Entity
public class UserVote extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User blogger;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id")
    @JsonIgnore
    private Blog blog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    @JsonIgnore
    private Comments comments;

    @Column(name = "vote", nullable = false)
    private Boolean vote;

    public User getBlogger() {
        return blogger;
    }

    public void setBlogger(User blogger) {
        this.blogger = blogger;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public Boolean getVote() {
        return vote;
    }

    public void setVote(Boolean vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "UserVote{" +
                "blogger=" + blogger +
                ", blog=" + blog +
                ", comments=" + comments +
                ", vote=" + vote +
                '}';
    }
}
