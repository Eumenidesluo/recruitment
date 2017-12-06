package com.eumenides.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.eumenides.compont.ServiceException;
import com.eumenides.compont.StatusCode;
import com.eumenides.service.RegisterValidate;
import com.eumenides.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by Eumenides on 2017/2/18.
 */
@Controller
public class RegisterController {
    @Resource
    private RegisterValidate service;
    @Autowired
    private UserService userService;


    @RequestMapping(value="/register",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map< String, Object> load(HttpServletRequest request) throws ParseException {
        String action = request.getParameter("action");
        String password = request.getParameter("password");
        Map<String, Object> result = new HashMap<>();
        System.out.println("-----r----"+action);

        if("register".equals(action)) {
            String email = request.getParameter("email");
            if (userService.isEmailUnique(email)==1){
//                RegisterValidate registerValidate=(RegisterValidate) SpringContextHolder.getBean("registerValidate");
                try {
                    service.processregister(password,email);
                } catch (Exception e) {
                	result.put("status", StatusCode.SQL_OP_ERR);
                	return result;
                }
            }
            else{
            	result.put("status", StatusCode.REPEAT_REGISTER);
            	return result;
            }
                
        }
        else if("activate".equals(action)) {
            String email = request.getParameter("email");
            String validateCode = request.getParameter("validateCode");

            try {
                service.processActivate(email,validateCode);
                result.put("status",StatusCode.SUCCESS);
                return result;
            } catch (ServiceException e) {
                request.setAttribute("message" , e.getMessage());
            }catch (ParseException e){
                result.put("status", StatusCode.UNKNOW_ERROR);
                return result;
            }

        }else {
        	 result.put("status", StatusCode.PARAMETER_ERROR);
             return result;
		}
        
        result.put("status", StatusCode.SUCCESS);
        return result;
    }
}
