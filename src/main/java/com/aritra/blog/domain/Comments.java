package com.aritra.blog.domain;

import org.hibernate.annotations.ManyToAny;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 9:59 AM
 * @project blogapp
 */
@Entity
@Table(name = "comments")
public class Comments extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "created_by_id", nullable = false)
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog;

    @Column(name = "comment")
    @Lob
    private String comment;
    private Date commentDate = new Date();

    @OneToMany(mappedBy = "comments")
    private List<UserVote> items = new ArrayList<>();

    private Boolean isDeleted = false;
    @ManyToOne
    @Nullable
    private User deletedBy;

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<UserVote> getItems() {
        return items;
    }

    public void setItems(List<UserVote> items) {
        this.items = items;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    @Nullable
    public User getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(@Nullable User deletedBy) {
        this.deletedBy = deletedBy;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "createdBy=" + createdBy +
                ", blog=" + blog +
                ", comment='" + comment + '\'' +
                ", commentDate=" + commentDate +
                ", items=" + items +
                ", isDeleted=" + isDeleted +
                ", deletedBy=" + deletedBy +
                '}';
    }
}
