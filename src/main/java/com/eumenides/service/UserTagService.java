package com.eumenides.service;

import com.eumenides.entity.UserTag;

import java.util.List;

/**
 * Created by Eumenides on 2017/11/28.
 */
public interface UserTagService {
    List<UserTag> queryByUserId(int userId);

    void updateTags(int userId);
}
