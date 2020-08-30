package com.aritra.blog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 9:58 AM
 * @project blogapp
 */
@Entity
public class Image extends BaseEntity {
    @Column(columnDefinition="LONGTEXT")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Image{" +
                "url='" + url + '\'' +
                '}';
    }
}
