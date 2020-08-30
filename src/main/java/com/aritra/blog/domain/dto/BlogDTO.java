package com.aritra.blog.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

import java.util.Date;
import java.util.List;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 10:58 AM
 * @project blogapp
 */
@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlogDTO {

    private Long id;
    private String title;
    private String genre;
    private String description;
    private UserDTO createdBy;
    private UserDTO approvedBy;
    private UserDTO deletedBy;
    private Date createdOn;
    private Long userId;
    private Boolean isVoted;
    private Boolean voteType;
    private Boolean isApproved;
    private Boolean isDeleted;
    private Integer totalComments;
    private Integer totalVotes;
    private List<String> imageUrls;
    private List<Long> imageIds;
    List<CommentDTO> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public UserDTO getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserDTO createdBy) {
        this.createdBy = createdBy;
    }

    public UserDTO getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(UserDTO approvedBy) {
        this.approvedBy = approvedBy;
    }

    public UserDTO getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(UserDTO deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getVoted() {
        return isVoted;
    }

    public void setVoted(Boolean voted) {
        isVoted = voted;
    }

    public Boolean getVoteType() {
        return voteType;
    }

    public void setVoteType(Boolean voteType) {
        this.voteType = voteType;
    }

    public Integer getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(Integer totalComments) {
        this.totalComments = totalComments;
    }

    public Integer getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(Integer totalVotes) {
        this.totalVotes = totalVotes;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<Long> getImageIds() {
        return imageIds;
    }

    public void setImageIds(List<Long> imageIds) {
        this.imageIds = imageIds;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "BlogDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                ", createdOn=" + createdOn +
                ", userId=" + userId +
                ", isVoted=" + isVoted +
                ", voteType=" + voteType +
                ", isApproved=" + isApproved +
                ", isDeleted=" + isDeleted +
                ", totalComments=" + totalComments +
                ", totalVotes=" + totalVotes +
                ", imageUrls=" + imageUrls +
                ", imageIds=" + imageIds +
                ", comments=" + comments +
                '}';
    }
}
