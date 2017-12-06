package com.eumenides.controller;

import com.eumenides.beans.RecruitBean;
import com.eumenides.compont.StatusCode;
import com.eumenides.entity.CollectUserRecruit;
import com.eumenides.service.CollectRecruitService;
import com.eumenides.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/recruit")
public class RecruitController {

	@Autowired
	RecruitService recruitService;
	@Autowired
	CollectRecruitService collectRecruitService;

	@RequestMapping(value = "/collectStatus")
	@ResponseBody
	public Map< String, Object> collectStatus(HttpSession session,@RequestParam Integer recruitId) {
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return result;
		}
		if (recruitId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return result;
		}
		
		if (collectRecruitService.findCollectOne(userId, recruitId)!=null) {
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
		
		List<?> list = collectRecruitService.findCollectsByUserId(userId);
		List<RecruitBean> collects = new ArrayList<>();
		if (list == null) {
			result.put("status", StatusCode.SUCCESS);
			result.put("collects", collects);
			return result;
		}
		for(Object o:list) {
			CollectUserRecruit entity = (CollectUserRecruit)o;
			RecruitBean recruitEntity = recruitService.findRecruitByRecruitId(entity.getRecruitid());
			if (recruitEntity != null) {
				collects.add(recruitEntity);
			}
		}
		result.put("status", StatusCode.SUCCESS);
		result.put("collects", collects);
		return result;
	}

	@RequestMapping(value = "/removeCollect")
	@ResponseBody
	public Map< String, Object> removeCollect(HttpSession session,@RequestParam Integer recruitId) {
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return result;
		}
		if (recruitId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return result;
		}
		if (collectRecruitService.deleteCollect(userId, recruitId)) {
			result.put("status", StatusCode.SUCCESS);
			return result;
		}
		result.put("status", StatusCode.SQL_OP_ERR);
		return result;
	}

	@RequestMapping(value = "collect")
	@ResponseBody
	public Map< String, Object> collect(HttpSession session,@RequestParam Integer recruitId){
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return result;
		}
		if (recruitId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return result;
		}
		CollectUserRecruit entity = new CollectUserRecruit();
		entity.setRecruitid(recruitId);
		entity.setUserid(userId);
		if (collectRecruitService.addCollect(entity)==-1) {
			result.put("status", StatusCode.SQL_OP_ERR);
			return result;
		}
		result.put("status", StatusCode.SUCCESS);
		return result;
	}

	@RequestMapping(value = "/search")
	@ResponseBody
	public Map< String, Object> search(HttpSession session,@RequestParam String text){
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
		List<RecruitBean> list = recruitService.findByKey(text);
		result.put("status", StatusCode.SUCCESS);
		result.put("list", list);
		return result;
	}

	@RequestMapping(value = "/query")
	@ResponseBody
 	public Map< String, Object> query(HttpServletRequest request,HttpSession session){
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return result;
		}
		String tag = request.getParameter("tag");
		String begin = request.getParameter("begin");
		if (begin == null){
			result.put("status", StatusCode.PARAMETER_ERROR);
			return result;
		}
		List<RecruitBean> list = recruitService.findRecruitsLimit(Integer.parseInt(begin), 10, tag);
		if (list == null) {
			result.put("status", StatusCode.MAX);
			return result;
		}
		result.put("status", StatusCode.SUCCESS);
		result.put("recruits", list);
		return result;
	}
}
