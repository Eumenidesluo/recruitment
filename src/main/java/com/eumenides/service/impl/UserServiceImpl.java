package com.eumenides.service.impl;

import com.eumenides.dao.UserMapper;
import com.eumenides.entity.User;
import com.eumenides.entity.UserExample;
import com.eumenides.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Eumenides on 2017/10/17.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByEmail(String email) {
        UserExample example = new UserExample();
        example.or().andEmailEqualTo(email);
        List<User> users = userMapper.selectByExample(example);
        if (users == null || users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public int isEmailUnique(String unique) {
        if (findByEmail(unique)!=null)
            return 1;
        return 0;
    }
}
