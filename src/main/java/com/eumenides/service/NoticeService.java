package com.eumenides.service;

import com.eumenides.entity.Notice;

import java.util.List;
import java.util.Map;

/**
 * Created by Eumenides on 2017/10/17.
 */
public interface NoticeService {
    List<Notice> queryByUserId(int userId,int type);
    List<Notice> queryByUserId(int userId);
    Boolean saveNotice(Notice notice);
    Boolean saveDynamicNotice(Notice notice, Map<String,String> params);
}
