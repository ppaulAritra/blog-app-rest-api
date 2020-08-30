package com.aritra.blog.repository;

import com.aritra.blog.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 10:19 AM
 * @project blogapp
 */

public interface ImageRepository extends JpaRepository<Image,Long> {
    Image findFirstById(Long id);
}
