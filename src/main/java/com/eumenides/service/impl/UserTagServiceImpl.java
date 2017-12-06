package com.eumenides.service.impl;

import com.eumenides.dao.UserTagMapper;
import com.eumenides.entity.*;
import com.eumenides.service.CollectProjectService;
import com.eumenides.service.ProjectService;
import com.eumenides.service.UserTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by Eumenides on 2017/11/28.
 */
@Service("userTagService")
public class UserTagServiceImpl implements UserTagService {
    @Autowired
    private UserTagMapper userTagMapper;
    @Autowired
    private CollectProjectService collectProjectService;
    @Autowired
    private ProjectService projectService;

    @Override
    public List<UserTag> queryByUserId(int userId) {
        if (userId == 0)
            return Collections.emptyList();
        UserTagExample example = new UserTagExample();
        example.or().andUserIdEqualTo(userId);
        return userTagMapper.selectByExample(example);
    }

    @Override
    public void updateTags(int userId) {
        if (userId == 0)
            return;
        List<CollectUserProject> collects = collectProjectService.findCollectsByUserId(userId);
        if (collects == null || collects.isEmpty())
            return;
        deleteByUserId(userId);
        List<String> tags = new ArrayList<>();
        if (collects.size() == 1) {
            Project project = projectService.queryProject(collects.get(0).getProjectid());
            if (!StringUtils.isEmpty(project.getLabel())) {
                tags.add(project.getLabel());
            }
        } else {
            Map<String,Integer> counter=new HashMap<>();
            for (CollectUserProject collect : collects) {
                Project project = projectService.queryProject(collect.getProjectid());
                if (project!=null&&!StringUtils.isEmpty(project.getLabel())){
                    countLabFrequency(counter,project.getLabel());
                }
            }
            tags=sortMap(counter);
        }
        addTags(tags,userId);
    }

    private static List<String> sortMap(Map map){
        List<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        if (entries.isEmpty())
            return new ArrayList<>();
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> obj1 , Map.Entry<String, Integer> obj2) {
                return obj2.getValue() - obj1.getValue();
            }
        });
        List<String> tags=new ArrayList<>();
        tags.add(entries.get(0).getKey());
        if (entries.size()>1)
            tags.add(entries.get(1).getKey());
        return tags;
    }
    private void countLabFrequency(Map<String,Integer> map,String lab){
        if (map.containsKey(lab)){
            map.put(lab,map.get(lab)+1);
        }else
            map.put(lab,0);
    }

    private void addTags(List<String> tags, int userId) {
        for (String tag : tags) {
            UserTag e = new UserTag();
            e.setTag(tag);
            e.setUserId(userId);
            userTagMapper.insert(e);
        }
    }

    private void deleteByUserId(int userId) {
        UserTagExample example = new UserTagExample();
        UserTagExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        userTagMapper.deleteByExample(example);
    }
}
