package com.eumenides.service.impl;

import com.eumenides.beans.MemberBean;
import com.eumenides.beans.RelateGroupProjectBean;
import com.eumenides.beans.RelateUserGroupBean;
import com.eumenides.dao.*;
import com.eumenides.entity.*;
import com.eumenides.service.GroupService;
import com.eumenides.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupMapper groupMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RelateUserGroupMapper ugRelateMapper;
    @Autowired
    PersonalInfMapper personalInfMapper;
    @Autowired
    RelateGroupProjectMapper relateGPMapper;
    @Autowired
    ProjectMapper projectMapper;
    @Autowired
    private ResumeService resumeService;

    public Integer createGroup(Integer creatorId, String gourpName) {
        Group groupEntity = new Group();
        groupEntity.setLeaderid(creatorId);
        groupEntity.setName(gourpName);
        return groupMapper.insertSelective(groupEntity);
    }

    @Override
    public Boolean inviteMember(Integer groupId, String email) {
        UserExample example = new UserExample();
        example.or().andEmailEqualTo(email);
        List<User> userEntities = userMapper.selectByExample(example);
        if (userEntities == null || userEntities.isEmpty()) {
            return false;
        }
        User userEntity = userEntities.get(0);
        RelateUserGroup entity = new RelateUserGroup();
        entity.setGroupid(groupId);
        entity.setUserid(userEntity.getId());
        entity.setPlace(1);
        if (ugRelateMapper.insertSelective(entity) <= 0) {
            return false;
        }
        return true;
    }


    public List<MemberBean> queryMembers(Integer groupId) {
        RelateUserGroupExample example = new RelateUserGroupExample();
        example.or().andGroupidEqualTo(groupId);
        List<RelateUserGroup> relateList = ugRelateMapper.selectByExample(example);
        List<MemberBean> members = new ArrayList<>();
        for (RelateUserGroup entity : relateList) {
            User user = userMapper.selectByPrimaryKey(entity.getUserid());
            PersonalInfExample personalInfExample = new PersonalInfExample();
            personalInfExample.or().andEmailEqualTo(user.getEmail());
            List<PersonalInf> psersonal = personalInfMapper.selectByExample(personalInfExample);
            if (psersonal == null || psersonal.isEmpty())
                continue;
            members.add(new MemberBean(user.getId(), psersonal.get(0).getName()));
        }
        return members;
    }

    public Boolean deleteMenber(Integer groupId, Integer deleteId) {
        RelateUserGroupExample example = new RelateUserGroupExample();
        RelateUserGroupExample.Criteria criteria = example.createCriteria();
        criteria.andGroupidEqualTo(groupId);
        criteria.andUseridEqualTo(deleteId);
        try {
            ugRelateMapper.deleteByExample(example);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<RelateUserGroupBean> getUserGroupInfo(Integer userId) {
        RelateUserGroupExample example = new RelateUserGroupExample();
        example.or().andUseridEqualTo(userId);
        List<RelateUserGroup> eList = ugRelateMapper.selectByExample(example);
        List<RelateUserGroupBean> list = new ArrayList<>();
        for (RelateUserGroup entity : eList) {
            Group groupEntity = groupMapper.selectByPrimaryKey(entity.getGroupid());
            list.add(new RelateUserGroupBean(entity, groupEntity.getName()));
        }
        return list;
    }

    @Override
    public List<RelateGroupProjectBean> findRelatesByGroupId(Integer groupId) {
        List<RelateGroupProjectBean> beanList = new ArrayList<>();
        RelateGroupProjectExample example = new RelateGroupProjectExample();
        example.or().andGroupidEqualTo(groupId);
        List<RelateGroupProject> entityList = relateGPMapper.selectByExample(example);
        if (entityList == null) {
            return null;
        }
        for (RelateGroupProject entity : entityList) {
            Project projectEntity = projectMapper.selectByPrimaryKey(entity.getProjectid());
            beanList.add(new RelateGroupProjectBean(entity.getProjectid(), entity.getIsentrusted(), projectEntity.getTitle()));
        }
        return beanList;
    }

    @Override
    public Group findGroupByLeaderId(Integer leader) {
        GroupExample example = new GroupExample();
        example.or().andLeaderidEqualTo(leader);
        try {
            return groupMapper.selectByExample(example).get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void setDefaultResume(Integer userId, Integer groupId, Integer resumeId) {
        RelateUserGroupExample example = new RelateUserGroupExample();
        RelateUserGroupExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userId);
        criteria.andGroupidEqualTo(groupId);
        List<RelateUserGroup> relates = ugRelateMapper.selectByExample(example);
        if (relates == null || relates.size() != 1)
            return;
        relates.get(0).setDefaultResume(resumeId);
        ugRelateMapper.updateByExample(relates.get(0), example);
    }

    @Override
    public Resume getDefaultResume(Integer userId, Integer groupId) {
        RelateUserGroupExample example = new RelateUserGroupExample();
        RelateUserGroupExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userId);
        criteria.andGroupidEqualTo(groupId);
        List<RelateUserGroup> relates = ugRelateMapper.selectByExample(example);
        if (relates == null || relates.size() != 1)
            return null;
        RelateUserGroup relateUserGroup = relates.get(0);
        if (relateUserGroup.getDefaultResume() == null || relateUserGroup.getDefaultResume() == 0)
            return null;
        return resumeService.queryResume(relateUserGroup.getDefaultResume());
    }
}
