package com.aritra.blog.repository;

import com.aritra.blog.domain.BasicInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 10:19 AM
 * @project blogapp
 */

public interface BasicInfoRepository extends JpaRepository<BasicInfo,Long> {
    BasicInfo findFirstByEmail(String email);
    BasicInfo findFirstByPhoneNo(String phoneNo);
}
