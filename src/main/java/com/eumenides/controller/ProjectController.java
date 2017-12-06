package com.eumenides.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.eumenides.compont.StatusCode;
import com.eumenides.entity.CollectUserProject;
import com.eumenides.entity.Project;
import com.eumenides.service.CollectProjectService;
import com.eumenides.service.ProjectService;
import com.eumenides.service.UserTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/project")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	@Autowired
	CollectProjectService collectProjectService;
	@Autowired
	private UserTagService userTagService;

	@RequestMapping(value = "/collectStatus")
	@ResponseBody
	public Map< String, Object> collectStatus(HttpSession session,@RequestParam Integer projectId) {
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return result;
		}
		if (projectId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return result;
		}
		
		if (collectProjectService.findCollectOne(userId, projectId)!=null) {
			result.put("status", StatusCode.SUCCESS);
			return result;
		}
		result.put("status", StatusCode.NOT_EXIST);
		return result;
	}

	@RequestMapping(value = "/queryCollects")
	@ResponseBody
	public Map< String, Object> queryCollects(HttpSession session) {
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return result;
		}
		
		List<?> list = collectProjectService.findCollectsByUserId(userId);
		if (list == null) {
			list = new ArrayList<>();
			result.put("status", StatusCode.SUCCESS);
			result.put("collects", list);
			return result;
		}
		List<Project> collects = new ArrayList<>();
		for(Object o:list) {
			CollectUserProject entity = (CollectUserProject)o;
			Project projectEntity = projectService.queryProject(entity.getProjectid());
			if (projectEntity != null) {
				collects.add(projectEntity);
			}
		}
		result.put("status", StatusCode.SUCCESS);
		result.put("collects", collects);
		return result;
	}

	@RequestMapping(value = "/removeCollect")
	@ResponseBody
	public Map< String, Object> removeCollect(HttpSession session,@RequestParam Integer projectId) {
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return result;
		}
		if (projectId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return result;
		}
		if (collectProjectService.deleteCollect(userId, projectId)) {
			result.put("status", StatusCode.SUCCESS);
			return result;
		}
		userTagService.updateTags(userId);
		result.put("status", StatusCode.SQL_OP_ERR);
		return result;
	}
	

	@RequestMapping(value = "collect")
	@ResponseBody
	public Map< String, Object> collect(HttpSession session,@RequestParam Integer projectId){
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return result;
		}
		if (projectId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return result;
		}
		CollectUserProject entity = new CollectUserProject();
		entity.setProjectid(projectId);
		entity.setUserid(userId);
		if (collectProjectService.addCollect(entity)==-1) {
			result.put("status", StatusCode.SQL_OP_ERR);
			return result;
		}
		userTagService.updateTags(userId);
		result.put("status", StatusCode.SUCCESS);
		return result;
	}

	@RequestMapping(value = "/search")
	@ResponseBody
	public Map< String, Object> search (HttpSession session,@RequestParam String text){
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return result;
		}
		
		if (text == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return result;
		}
		List<?> list = projectService.findProjectByKey(text);
		if (list == null) {
			list = new ArrayList<>();
		}
		result.put("status", StatusCode.SUCCESS);
		result.put("list", list);
		return result;
	}

	@RequestMapping(value = "/release")
	@ResponseBody
	public Map< String, Object> releaseProject(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return result;
		}
    	
		String projectJson = request.getParameter("projectJson");
		if (projectJson == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return result;
		}
		Project projectEntity = JSON.parseObject(projectJson, Project.class);
		Integer projectId = projectService.releaseProject(projectEntity);
		if (projectId == null) {
			result.put("status", StatusCode.SQL_OP_ERR);
			return result;
		}
		result.put("status", StatusCode.SUCCESS);
		result.put("projectId", projectId);
		return result;
	}
	

	@RequestMapping(value = "/list")
	@ResponseBody
	public Map< String, Object> list(HttpServletRequest request,HttpSession session) {
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return result;
		}
    	String begin = request.getParameter("count");
    	if (begin == null) {
    		result.put("status", StatusCode.PARAMETER_ERROR);
			return result;
		}
    	
    	List<?> projectList = projectService.queryManyProjects(Integer.parseInt(begin), 10);
    	if (projectList == null) {
			projectList = new ArrayList<>();
		}
    	result.put("status", StatusCode.SUCCESS);
    	result.put("projects", projectList);
		return result;
	}

	@RequestMapping(value = "/cancel")
	@ResponseBody
	public Map< String, Object> cacelProject(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return result;
		}
    	
		String projectId = request.getParameter("projectId");
		if (projectId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return result;
		}
		if (projectService.deleteProject(Integer.parseInt(projectId))) {
			result.put("status", StatusCode.SUCCESS);
			return result;
		}
		result.put("status", StatusCode.UNKNOW_ERROR);
		return result;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public Map<String,Object> create(@RequestBody Project project, HttpSession session){
		Map<String, Object> result = new HashMap<>();
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return result;
		}
		projectService.addProject(project);
		result.put("status", StatusCode.UNKNOW_ERROR);
		return result;
	}
	@RequestMapping(value="/query")
	@ResponseBody
	public Map< String, Object> queryyProject(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return result;
		}
    	
		String projectId = request.getParameter("projectId");
		if (projectId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return result;
		}
		Project entity = projectService.queryProject(Integer.parseInt(projectId));
		if (entity == null) {
			result.put("status", StatusCode.SQL_OP_ERR);
			return result;
		}else {
			result.put("status", StatusCode.SUCCESS);
			result.put("project", entity);
			return result;
		}
	}
	

	@RequestMapping(value="/update")
	@ResponseBody
	public Map< String, Object> updateProject(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return result;
		}
    	
		String projectJson = request.getParameter("projectJson");
		if (projectJson == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return result;
		}
		Project projectEntity = JSON.parseObject(projectJson,Project.class);
		if (projectEntity == null) {
			result.put("status", StatusCode.INFORMATION_PARSE_FAILED);
			return result;
		}
		if (projectService.updateProject(projectEntity)) {
			result.put("status", StatusCode.SUCCESS);
			return result;
		}
		result.put("status", StatusCode.UNKNOW_ERROR);
		return result;
	}
	
}
