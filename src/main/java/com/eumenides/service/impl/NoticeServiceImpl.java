package com.eumenides.service.impl;

import com.eumenides.dao.NoticeMapper;
import com.eumenides.dao.NoticeParamMapper;
import com.eumenides.entity.Notice;
import com.eumenides.entity.NoticeExample;
import com.eumenides.entity.NoticeParam;
import com.eumenides.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Eumenides on 2017/10/18.
 */
@Service
public class NoticeServiceImpl implements NoticeService{
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private NoticeParamMapper noticeParamMapper;
    @Override
    public List<Notice> queryByUserId(int userId, int type) {
        return queryByUserId(userId);
    }

    @Override
    public List<Notice> queryByUserId(int userId) {
        NoticeExample example=new NoticeExample();
        NoticeExample.Criteria criteria=example.createCriteria();
        criteria.andUseridEqualTo(userId);
        return noticeMapper.selectByExample(example);
    }

    @Override
    public Boolean saveNotice(Notice notice) {
        if (noticeMapper.insert(notice)<=0)
            return false;
        return true;
    }

    @Override
    public Boolean saveDynamicNotice(Notice notice, Map<String, String> params) {
        if (notice==null)
            return false;
        if (noticeMapper.insert(notice)<=0)
            return false;
        for (Map.Entry<String,String> entry:params.entrySet()){
            NoticeParam param=new NoticeParam();
            param.setNoticeId(notice.getNoticeid());
            param.setParamKey(entry.getKey());
            param.setParamValue(entry.getValue());
            noticeParamMapper.insert(param);
        }
        return true;
    }
}
