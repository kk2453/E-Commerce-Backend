package com.myProject.ECommerce.service;

import com.myProject.ECommerce.entity.User;
import com.myProject.ECommerce.exception.UserException;

public interface UserService {

    public User findUserById(Long userId) throws UserException;
    public User findUserProfileByJwt(String jwt) throws UserException;
}
