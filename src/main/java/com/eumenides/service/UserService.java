package com.eumenides.service;

import com.eumenides.entity.User;

/**
 * Created by Eumenides on 2017/10/17.
 */
public interface UserService {
    User findByEmail(String email);
    int isEmailUnique(String unique);
}
