package com.aritra.blog.repository;

import com.aritra.blog.domain.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 10:18 AM
 * @project blogapp
 */

public interface BlogRepository extends JpaRepository<Blog,Long> {
    Blog findFirstById(Long id);
    @Query(value = "SELECT * FROM blog where is_deleted IS False AND is_approved IS TRUE And post_date>=:fromDate  order by post_date DESC ",nativeQuery = true)
    Page<Blog> findAllApprovedBlogPostInBetweenDate(@Param("fromDate") String fromDate,
                                            Pageable pageable);
    @Query(value = "SELECT * FROM blog where is_deleted IS False AND is_approved IS FALSE And post_date>=:fromDate  order by post_date DESC ",nativeQuery = true)
    Page<Blog> findAllNotApprovedBlogPostInBetweenDate(@Param("fromDate") String fromDate,
                                                     Pageable pageable);
    @Query(value = "SELECT * FROM blog where is_deleted IS False And created_by_id=:bloggerId order by post_date DESC ",nativeQuery = true)
    Page<Blog> findAllBlogPostOfUser(@Param("bloggerId") Long bloggerId,Pageable pageable);

}
