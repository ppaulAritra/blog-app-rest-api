package com.aritra.blog.common.exception;

/**
 * @author Aritra Paul
 * @created_on 8/28/20 at 11:44 AM
 * @project blogapp
 */

public class EntityExistException extends RuntimeException {

    public EntityExistException(String message) {
        super(message);
    }
}
