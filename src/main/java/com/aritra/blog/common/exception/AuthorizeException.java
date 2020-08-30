package com.aritra.blog.common.exception;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 3:05 PM
 * @project blogapp
 */

public class AuthorizeException extends RuntimeException{
    public AuthorizeException(String message) {
        super(message);
    }
}
