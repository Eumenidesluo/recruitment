package com.eumenides.service.impl;

import com.eumenides.dao.ProjectStatusMapper;
import com.eumenides.dao.RelateGroupProjectMapper;
import com.eumenides.entity.ProjectStatus;
import com.eumenides.entity.RelateGroupProject;
import com.eumenides.entity.RelateGroupProjectExample;
import com.eumenides.service.ProjectStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectStatusServiceImpl implements ProjectStatusService {
    @Autowired
    ProjectStatusMapper projectStatusMapper;
    @Autowired
    RelateGroupProjectMapper relateGPMapper;


    public Boolean appoint(Integer projectId, Integer appointor) {
        Boolean flag = true;
        RelateGroupProjectExample example=new RelateGroupProjectExample();
        RelateGroupProjectExample.Criteria criteria=example.createCriteria();
        criteria.andProjectidEqualTo(projectId);
        criteria.andGroupidEqualTo(appointor);
        List<RelateGroupProject> entities = relateGPMapper.selectByExample(example);
        RelateGroupProjectExample example1=new RelateGroupProjectExample();
        example1.or().andProjectidEqualTo(projectId);
        List<RelateGroupProject> list = relateGPMapper.selectByExample(example1);
        if (entities == null||entities.isEmpty()) {
            return false;
        }
        for (RelateGroupProject relate : list) {

            if (relate.getIsentrusted() == 1) {
                flag = false;
                break;
            }
        }
        if (!flag) {
            return false;
        }
        RelateGroupProject entity=null;
        entity=entities.get(0);
        entity.setIsentrusted(1);
        relateGPMapper.updateByPrimaryKeySelective(entity);
        return true;
    }

    public Boolean apply(Integer projectId, Integer groupId) {
        RelateGroupProject entity = new RelateGroupProject();
        entity.setGroupid(groupId);
        entity.setProjectid(projectId);
        entity.setIsentrusted(0);
        if (relateGPMapper.insertSelective(entity) <= 0)
            return false;
        return true;
    }

    public Boolean finish(Integer projectId, Integer groupId) {
        ProjectStatus statusEntity = projectStatusMapper.selectByPrimaryKey(projectId);
        if (statusEntity == null) {
            return false;
        }
        if (statusEntity.getIsfinish() == 1) {
            return false;
        }
        statusEntity.setIsfinish(1);
        projectStatusMapper.updateByPrimaryKeySelective(statusEntity);
        return true;
    }

    @Override
    public Integer status(Integer projectId, Integer groupId) {
        RelateGroupProjectExample example=new RelateGroupProjectExample();
        RelateGroupProjectExample.Criteria criteria=example.createCriteria();
        criteria.andProjectidEqualTo(projectId);
        criteria.andGroupidEqualTo(groupId);
        List<RelateGroupProject> entity = relateGPMapper.selectByExample(example);
        if (entity == null||entity.isEmpty()) {
            return -1;
        }

        return entity.get(0).getIsentrusted();
    }

}
