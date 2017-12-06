package com.eumenides.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.eumenides.compont.StatusCode;
import com.eumenides.entity.Notice;
import com.eumenides.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/notice")
public class NoticeController {

	@Autowired
	NoticeService noticeService;

	@RequestMapping(value = "/send")
	@ResponseBody
	public Map< String, Object> sendNotice(@RequestParam("notice") String notice,
							@RequestParam("sendTo")Integer sendto,
							HttpSession session) {
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return result;
		}
		if (notice == null || sendto == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return result;
		}
		Notice entity = JSON.parseObject(notice,Notice.class);
		if (noticeService.saveNotice(entity)) {
			result.put("status", StatusCode.SUCCESS);
			return result;
		}
		
		result.put("status", StatusCode.UNKNOW_ERROR);
		return result;
	}

	@RequestMapping(value = "/query")
	@ResponseBody
	public Map< String, Object> query(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		String number = request.getParameter("number");
		if ( number == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return result;
		}
		if (Integer.parseInt(number) == -1) {
			List<?> notices = noticeService.queryByUserId(userId);
			result.put("status", StatusCode.SUCCESS);
			result.put("notices", notices);
			return result;
		}else {
			List<Notice> notices = noticeService.queryByUserId(userId,Integer.parseInt(number));
			result.put("status", StatusCode.SUCCESS);
			result.put("notices", notices);
			return result;
		}		
	}
}
