package com.eumenides.service.impl;

import com.eumenides.dao.CollectUserRecruitMapper;
import com.eumenides.entity.CollectUserRecruit;
import com.eumenides.entity.CollectUserRecruitExample;
import com.eumenides.service.CollectRecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Eumenides on 2017/10/18.
 */
@Service
public class CollectRecruitServiceImpl implements CollectRecruitService {
    @Autowired
    private CollectUserRecruitMapper collectUserRecruitMapper;

    @Override
    public Integer addCollect(CollectUserRecruit entity) {
        return collectUserRecruitMapper.insert(entity);
    }

    @Override
    public Boolean deleteCollect(Integer userId, Integer recruitId) {
        CollectUserRecruitExample example = new CollectUserRecruitExample();
        CollectUserRecruitExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userId);
        criteria.andRecruitidEqualTo(recruitId);
        if (collectUserRecruitMapper.deleteByExample(example) <= 0)
            return false;
        return true;
    }

    @Override
    public void updateCollect(CollectUserRecruit entity) {
        collectUserRecruitMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<?> findCollectsByUserId(int userId) {
        CollectUserRecruitExample example = new CollectUserRecruitExample();
        CollectUserRecruitExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userId);
        return collectUserRecruitMapper.selectByExample(example);
    }

    @Override
    public List<?> findCollectsByRecruitId(int recruitId) {
        CollectUserRecruitExample example = new CollectUserRecruitExample();
        CollectUserRecruitExample.Criteria criteria = example.createCriteria();
        criteria.andRecruitidEqualTo(recruitId);
        return collectUserRecruitMapper.selectByExample(example);
    }

    @Override
    public CollectUserRecruit findCollectOne(Integer userId, Integer recruitId) {
        CollectUserRecruitExample example = new CollectUserRecruitExample();
        CollectUserRecruitExample.Criteria criteria = example.createCriteria();
        criteria.andRecruitidEqualTo(recruitId);
        criteria.andUseridEqualTo(userId);
        List<CollectUserRecruit> collects =collectUserRecruitMapper.selectByExample(example);
        if (collects!=null&&!collects.isEmpty())
            return collects.get(0);
        return null;
    }
}
