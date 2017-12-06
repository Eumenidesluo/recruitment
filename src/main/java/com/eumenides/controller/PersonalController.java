package com.eumenides.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.eumenides.compont.StatusCode;
import com.eumenides.entity.PersonalInf;
import com.eumenides.service.PersonalInfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by Eumenides on 2017/2/19.
 */
@Controller
@RequestMapping(value = "/personal")
public class PersonalController {
    @Autowired
	PersonalInfService personService;
    

	@RequestMapping(value = "/update")
    @ResponseBody
    public Map< String, Object> execute(HttpServletRequest request,HttpSession session){
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return result;
		}
    	String personJson = request.getParameter("personJson");
    	if (personJson == null) {
    		result.put("status", StatusCode.PARAMETER_ERROR);
			return result;
		}
    	PersonalInf entity = JSON.parseObject(personJson,PersonalInf.class);
    	if (personService.updatePersonInformation(entity)) {
    		result.put("status", StatusCode.SUCCESS);
			return result;
		}else{
			result.put("status", StatusCode.NOT_EXIST);
			return result;
		}
			
    }
	

	@RequestMapping(value = "/query")
	@ResponseBody
	public Map< String, Object> queryPersonal(HttpServletRequest request,HttpSession session){
		Map< String, Object> result = new HashMap<>();
		String personId = request.getParameter("person");
		if (personId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return result;
		}
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null||userId != Integer.parseInt(personId)) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return result;
		}
		PersonalInf entity = personService.getPersonById(personId);
		if (entity == null) {
			result.put("status", StatusCode.NOT_EXIST);
			return result;
		}else{
			result.put("status", StatusCode.SUCCESS);
			result.put("personal", entity);
			return result;
		}
	}
}
