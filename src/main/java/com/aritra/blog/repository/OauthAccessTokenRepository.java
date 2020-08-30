package com.aritra.blog.repository;

import com.aritra.blog.domain.authentication.OAuthAccessToken;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 3:51 PM
 * @project blogapp
 */

public interface OauthAccessTokenRepository extends CrudRepository<OAuthAccessToken, String> {
    OAuthAccessToken getFirstByUserName(String username);

    void deleteByUserName(String username);
}
