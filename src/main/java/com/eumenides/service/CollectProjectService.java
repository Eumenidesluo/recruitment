package com.eumenides.service;

import com.eumenides.entity.CollectUserProject;

import java.util.List;

/**
 * Created by Eumenides on 2017/10/17.
 */
public interface CollectProjectService {
    Integer addCollect(CollectUserProject entity);

    Boolean deleteCollect(Integer userId, Integer projectId);

    List<CollectUserProject> findCollectsByUserId(int userId);

    List<CollectUserProject> findCollectsByProjectId(int projectId);

    CollectUserProject findCollectOne(Integer userId, Integer projectId);
}
