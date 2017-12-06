package com.eumenides.service.impl;

import com.eumenides.dao.CollectUserProjectMapper;
import com.eumenides.entity.CollectUserProject;
import com.eumenides.entity.CollectUserProjectExample;
import com.eumenides.service.CollectProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Eumenides on 2017/10/18.
 */
@Service
public class CollectProjectServiceImpl implements CollectProjectService {
    @Autowired
    private CollectUserProjectMapper collectUserProjectMapper;

    @Override
    public Integer addCollect(CollectUserProject entity) {
        return collectUserProjectMapper.insert(entity);
    }

    @Override
    public Boolean deleteCollect(Integer userId, Integer projectId) {
        CollectUserProjectExample example = new CollectUserProjectExample();
        CollectUserProjectExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userId);
        criteria.andProjectidEqualTo(projectId);
        if (collectUserProjectMapper.deleteByExample(example) <= 0)
            return false;
        return true;
    }

    @Override
    public List<CollectUserProject> findCollectsByUserId(int userId) {
        CollectUserProjectExample example = new CollectUserProjectExample();
        example.or().andUseridEqualTo(userId);
        return collectUserProjectMapper.selectByExample(example);
    }

    @Override
    public List<CollectUserProject> findCollectsByProjectId(int projectId) {
        CollectUserProjectExample example = new CollectUserProjectExample();
        example.or().andProjectidEqualTo(projectId);
        return collectUserProjectMapper.selectByExample(example);
    }

    @Override
    public CollectUserProject findCollectOne(Integer userId, Integer projectId) {
        CollectUserProjectExample example = new CollectUserProjectExample();
        CollectUserProjectExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userId);
        criteria.andProjectidEqualTo(projectId);
        List<CollectUserProject> collects = collectUserProjectMapper.selectByExample(example);
        if (collects != null && !collects.isEmpty())
            return collects.get(0);
        return null;
    }
}
