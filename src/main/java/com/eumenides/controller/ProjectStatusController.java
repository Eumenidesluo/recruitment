package com.eumenides.controller;

import com.eumenides.compont.StatusCode;
import com.eumenides.entity.Group;
import com.eumenides.entity.Notice;
import com.eumenides.service.GroupService;
import com.eumenides.service.NoticeService;
import com.eumenides.service.ProjectStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value = "bid")
public class ProjectStatusController {

	@Autowired
	ProjectStatusService projectStatusService;
	@Autowired
	GroupService groupService;
	@Autowired
	private NoticeService noticeService;


	@RequestMapping(value = "/init")
	@ResponseBody
	public Map< String, Object> init(HttpSession session,@RequestParam Integer projectId) {
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return result;
		}//��¼��֤
    	if (projectId == null) {
    		result.put("status", StatusCode.PARAMETER_ERROR);
			return result;
		}
    	Group entity = groupService.findGroupByLeaderId(userId);
    	if (entity == null) {
    		result.put("status", StatusCode.NOT_EXIST);
			return result;
		}
    	Integer bidStatus = projectStatusService.status(projectId, entity.getGroupid());
    	if (bidStatus == -1) {
    		result.put("status", StatusCode.NOT_EXIST);
			return result;
		}else if (bidStatus == 0) {
			result.put("status", StatusCode.SUCCESS);
			return result;
		}else if (bidStatus == 1) {
			result.put("status", StatusCode.APPOINT);
			return result;
		}
    	result.put("status", StatusCode.UNKNOW_ERROR);
    	return result;
	}

	@RequestMapping(value = "/apply")
	@ResponseBody
	public Map< String, Object> apply(HttpServletRequest request,HttpSession session){
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
		Group entity  = groupService.findGroupByLeaderId(userId);
		if (entity == null) {
			result.put("status", StatusCode.NOT_EXIST);
			return result;
		}
		if (projectStatusService.apply(Integer.parseInt(projectId), entity.getGroupid())) {
			Notice notice=new Notice();
			notice.setUserid(userId);
			notice.setTime(new Date());
			notice.setType(1);
			Map<String,String> params=new HashMap<>();
			params.put("groupId",entity.getGroupid().toString());
			params.put("groupName",entity.getName());
			noticeService.saveDynamicNotice(notice,params);
			result.put("status", StatusCode.SUCCESS);
			return result;
		}
		result.put("status", StatusCode.UNKNOW_ERROR);
		return result;
	}
	

	@RequestMapping(value = "/appoint")
	@ResponseBody
	public Map< String, Object> appoint(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return result;
		}
    	
		String projectId = request.getParameter("projectId");
		String groupId = request.getParameter("groupId");		
		if (projectId == null || groupId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return result;
		}
		
		try {
			if (projectStatusService.appoint(Integer.parseInt(projectId), Integer.parseInt(projectId))) {
				result.put("status", StatusCode.SUCCESS);
				return result;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			result.put("status", StatusCode.UNKNOW_ERROR);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result.put("status", StatusCode.UNKNOW_ERROR);
		}
		return result;
	}
	
	
	@RequestMapping(value = "/finish")
	@ResponseBody
	public Map< String, Object> finish(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return result;
		}
    	
		String projectId = request.getParameter("projectId");
		String groupId = request.getParameter("groupId");
		
		if (projectId == null || groupId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return result;
		}
		
		if (projectStatusService.finish(Integer.parseInt(projectId), Integer.parseInt(groupId))) {
			result.put("status", StatusCode.SUCCESS);
			return result;
		}
		result.put("status", StatusCode.UNKNOW_ERROR);
		return result;
	}
}
