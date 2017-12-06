package com.eumenides.service.impl;

import java.util.List;

import com.eumenides.dao.CompanyMapper;
import com.eumenides.dao.ProjectMapper;
import com.eumenides.entity.Company;
import com.eumenides.entity.Project;
import com.eumenides.entity.ProjectExample;
import com.eumenides.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("projectService")
public class ProjectServiceImp implements ProjectService {

    @Autowired
    ProjectMapper projectMapper;
    @Autowired
    CompanyMapper companyMapper;

    public Integer releaseProject(Project projectEntity) {
        return projectMapper.insertSelective(projectEntity);
    }

    public Boolean updateProject(Project projectEntity) {
        if (projectMapper.updateByPrimaryKeySelective(projectEntity) != 1)
            return false;
        return true;
    }

    public Boolean deleteProject(Integer projectId) {
        Project entity = projectMapper.selectByPrimaryKey(projectId);
        if (entity == null) {
            return false;
        }
        if (projectMapper.deleteByPrimaryKey(entity.getProjectid()) != 1)
            return false;
        return true;
    }

    public Project queryProject(Integer projectId) {
        Project projectEntity = projectMapper.selectByPrimaryKey(projectId);
        if (projectEntity == null) {
            return null;
        }
        Company companyEntity = companyMapper.selectByPrimaryKey(projectEntity.getCompanyid());
        if (companyEntity == null) {
            return null;
        }
        projectEntity.setCompanyname(companyEntity.getName());
        return projectEntity;
    }

    public List<Project> queryManyProjects(int begin, int max) {
        List<Project> list = projectMapper.findProjectsLimit(begin, max);
        if (list.size() == 0) {
            return null;
        }
        for (Object o : list) {
            Project entity = (Project) o;
            Company companyEntity = companyMapper.selectByPrimaryKey(entity.getCompanyid());
            if (companyEntity == null) {
                return null;
            }
            entity.setCompanyname(companyEntity.getName());
        }
        return list;
    }

    @Override
    public List<?> findProjectByKey(String key) {
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria titleCriteria = example.createCriteria();
        titleCriteria.andTitleLike("%" + key + "%");
        ProjectExample.Criteria labelCriteria=example.createCriteria();
        labelCriteria.andLabelEqualTo(key);
        example.or(labelCriteria);
        example.or(titleCriteria);
        example.setDistinct(true);
        return projectMapper.selectByExample(example);
    }

    @Override
    public List<Project> queryProjectsByTags(List<String> tags) {
        ProjectExample example = new ProjectExample();
        if (tags != null && !tags.isEmpty())
            example.or().andLabelIn(tags);
        return projectMapper.selectByExample(example);
    }

    @Override
    public void addProject(Project project) {
        if (project != null)
            projectMapper.insertSelective(project);
    }
}
