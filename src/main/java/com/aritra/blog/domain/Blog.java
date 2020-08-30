package com.aritra.blog.domain;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Aritra Paul
 * @created_on 8/27/20 at 12:04 AM
 * @project blogapp
 */
@Entity
@Table(name = "blog")
public class Blog extends BaseEntity {
    @NotNull
    @Column(name = "title")
    private String title;

    @Column(name = "genre")
    private String genre;

    @Lob
    @Column(name = "description", columnDefinition = "text")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "created_by_id", nullable = false)
    private User createdBy;
    @ManyToOne
    @Nullable
    private User approvedBy;
    @ManyToOne
    @Nullable
    private User deletedBy;

    @OneToMany(mappedBy = "blog")
    private List<UserVote> items = new ArrayList<>();

    private Boolean isDeleted = false;
    private Boolean isApproved = false;
    private Date postDate;
    @OneToMany
    List<Image> image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
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

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public User getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(User approvedBy) {
        this.approvedBy = approvedBy;
    }

    public User getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(User deletedBy) {
        this.deletedBy = deletedBy;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                ", createdBy=" + createdBy +
                ", items=" + items +
                ", isDeleted=" + isDeleted +
                ", isApproved=" + isApproved +
                ", postDate=" + postDate +
                ", image=" + image +
                '}';
    }
}
