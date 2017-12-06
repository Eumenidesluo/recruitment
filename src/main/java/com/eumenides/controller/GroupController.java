package com.eumenides.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.eumenides.beans.MemberBean;
import com.eumenides.beans.RelateGroupProjectBean;
import com.eumenides.beans.RelateUserGroupBean;
import com.eumenides.compont.StatusCode;
import com.eumenides.entity.Resume;
import com.eumenides.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author Eumenides
 */
@Controller
@RequestMapping(value = "/group")
public class GroupController {

    @Autowired
    private GroupService groupService;


    @RequestMapping(value = "/relate")
    @ResponseBody
    public Map<String, Object> applying(HttpSession session, @RequestParam Integer groupId) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            result.put("status", StatusCode.AUTHENTICATION_FAILED);
            return result;
        }
        if (groupId == null) {
            result.put("status", StatusCode.PARAMETER_ERROR);
            return result;
        }
        List<RelateGroupProjectBean> beanList = groupService.findRelatesByGroupId(groupId);
        if (beanList == null) {
            beanList = new ArrayList<RelateGroupProjectBean>();
        }
        result.put("status", StatusCode.SUCCESS);
        result.put("relates", beanList);
        return result;

    }

    @RequestMapping(value = "/init")
    @ResponseBody
    public Map<String, Object> init(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            result.put("status", StatusCode.AUTHENTICATION_FAILED);
            return result;
        }
        List<RelateUserGroupBean> list = groupService.getUserGroupInfo(userId);
        result.put("status", StatusCode.SUCCESS);
        result.put("informations", list);
        return result;
    }


    @RequestMapping(value = "/create")
    @ResponseBody
    public Map<String, Object> createGroup(HttpSession session, @RequestParam String groupName) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            result.put("status", StatusCode.AUTHENTICATION_FAILED);
            return result;
        }
        if (groupName == null) {
            result.put("status", StatusCode.PARAMETER_ERROR);
            return result;
        }

        Integer newGroupId = groupService.createGroup(userId, groupName);
        if (newGroupId == null) {
            result.put("status", StatusCode.SQL_OP_ERR);
            return result;
        }
        result.put("status", StatusCode.SUCCESS);
        result.put("groupId", newGroupId);
        return result;
    }

    @RequestMapping(value = "/invite")
    @ResponseBody
    public Map<String, Object> inviteJoin(HttpServletRequest request, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            result.put("status", StatusCode.AUTHENTICATION_FAILED);
            return result;
        }

        String groupId = request.getParameter("groupId");
        String email = request.getParameter("email");

        if (groupId == null || email == null) {
            result.put("status", StatusCode.PARAMETER_ERROR);
            return result;
        }
        if (groupService.inviteMember(Integer.parseInt(groupId), email)) {
            result.put("status", StatusCode.SUCCESS);
            return result;
        }
        result.put("status", StatusCode.UNKNOW_ERROR);
        return result;
    }

    @RequestMapping(value = "/deleteMember")
    @ResponseBody
    public Map<String, Object> deleteMember(HttpSession session, @RequestParam Integer groupId, @RequestParam(required = false) Integer deleteId) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            result.put("status", StatusCode.AUTHENTICATION_FAILED);
            return result;
        }
        if (groupId == null) {
            result.put("status", StatusCode.PARAMETER_ERROR);
            return result;
        }
        if (deleteId == null) {
            deleteId = userId;
        }
        if (groupService.deleteMenber(groupId, deleteId)) {
            result.put("status", StatusCode.SUCCESS);
            return result;
        }
        result.put("status", StatusCode.UNKNOW_ERROR);
        return result;
    }

    @RequestMapping(value = "/query")
    @ResponseBody
    public Map<String, Object> findGroup(@RequestParam Integer groupId, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            result.put("status", StatusCode.AUTHENTICATION_FAILED);
            return result;
        }

        if (groupId == null) {
            result.put("status", StatusCode.PARAMETER_ERROR);
            return result;
        }
        List<MemberBean> memebers = groupService.queryMembers(groupId);
        result.put("status", StatusCode.SUCCESS);
        result.put("members", memebers);
        return result;
    }

    @RequestMapping("/setDefaultResume")
    @ResponseBody
    public Map<String, Object> setDefaultResume(Integer resumeId, Integer groupId, HttpSession httpSession) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer) httpSession.getAttribute("userId");
        if (userId == null) {
            result.put("status", StatusCode.AUTHENTICATION_FAILED);
            return result;
        }
        groupService.setDefaultResume(userId, groupId, resumeId);
        result.put("status", StatusCode.SUCCESS);
        return result;
    }

    @RequestMapping("/getDefaultResume")
    @ResponseBody
    public Map<String, Object> getDefaultResume(Integer groupId, HttpSession httpSession) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer) httpSession.getAttribute("userId");
        if (userId == null) {
            result.put("status", StatusCode.AUTHENTICATION_FAILED);
            return result;
        }
        Resume resume = groupService.getDefaultResume(userId, groupId);
        result.put("resumeName", resume.getName());
        result.put("status", StatusCode.SUCCESS);
        return result;
    }
}
