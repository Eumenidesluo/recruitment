package com.eumenides.service;

import com.eumenides.entity.Project;

import java.util.List;



public interface ProjectService {

	public Integer releaseProject(Project projectEntity);
	
	public Boolean updateProject(Project projectEntity);
	
	public Boolean deleteProject(Integer projectId);
	
	public Project queryProject(Integer projectId);
	
	public List<?> queryManyProjects(int begin,int max);
	
	public List<?> findProjectByKey(String key);

	List<Project> queryProjectsByTags(List<String> tags);

	void addProject(Project project);
}
