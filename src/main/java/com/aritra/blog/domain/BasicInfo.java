package com.aritra.blog.domain;

import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 10:07 AM
 * @project blogapp
 */
@Entity
public class BasicInfo extends BaseEntity {
    private String name;
    private String phoneNo;
    private String email;
    @OneToOne
    @Nullable
    private Image image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "BasicInfo{" +
                "name='" + name + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", email='" + email + '\'' +
                ", image=" + image +
                '}';
    }
}
