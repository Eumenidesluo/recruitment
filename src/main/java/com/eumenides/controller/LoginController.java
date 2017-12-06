package com.eumenides.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eumenides.beans.RecruitBean;
import com.eumenides.compont.StatusCode;
import com.eumenides.entity.*;
import com.eumenides.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by Eumenides on 2017/2/18.
 */


@Controller
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    PersonalInfService personalInfService;
    @Autowired
    RecruitService recruitService;
    @Autowired
    NoticeService noticeService;
    @Autowired
    UserService userService;
    @Autowired
    ProjectService projectService;
    @Autowired
    private UserTagService userTagService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request, HttpSession session) {
        String email;
        String password;
        Map<String, Object> result = new HashMap<>();
        email = request.getParameter("email");
        password = request.getParameter("password");

        User userEntity = userService.findByEmail(email);

        if (userEntity == null)
            result.put("status", StatusCode.NOT_EXIST);
        else if (userEntity.getStatus() == 0) {
            result.put("status", StatusCode.NOT_ACTIOVE);
        } else {
            if (userEntity.getPassword().equals(password)) {
                session.setAttribute("userId", userEntity.getId());
                result.put("status", StatusCode.SUCCESS);
                result.put("userId", userEntity.getId());
            } else
                result.put("status", StatusCode.PASSWORD_OR_EMAIL_WRONG);
        }
        return result;
    }

    @RequestMapping(value = "/getInformation")
    @ResponseBody
    public Map<String, Object> loginGetInformation(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            result.put("status", StatusCode.AUTHENTICATION_FAILED);
            return result;
        }
        PersonalInf personalInfEntity = personalInfService.getPersonById(userId.toString());
        List<UserTag> userTags = userTagService.queryByUserId(userId);
        List<Project> projects = null;
        if (userTags != null && !userTags.isEmpty()) {
            List<String> tags = new ArrayList<>();
            for (UserTag u : userTags) {
                tags.add(u.getTag());
            }
            projects = projectService.queryProjectsByTags(tags);
        }
        List<Notice> notice = noticeService.queryByUserId(userId, 2);
        result.put("status", StatusCode.SUCCESS);
        result.put("personal", personalInfEntity);
        result.put("projects", projects);
        result.put("notices", notice);
        return result;
    }
}
