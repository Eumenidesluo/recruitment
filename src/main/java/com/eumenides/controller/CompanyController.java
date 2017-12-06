package com.eumenides.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.eumenides.compont.StatusCode;
import com.eumenides.entity.Company;
import com.eumenides.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/company")
public class CompanyController {
	@Autowired
	private CompanyService companyService;

	@RequestMapping(value = "/search")
	@ResponseBody
	public Map< String, Object> search(@RequestParam String text,HttpSession session) {
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
		
		List<Company> list = companyService.findCompanyesByKey(text);
		if (list == null) {
			result.put("status", StatusCode.SQL_OP_ERR);
			return result;
		}
		result.put("status", StatusCode.SUCCESS);
		result.put("companies", list);
		return result;
	}
}
