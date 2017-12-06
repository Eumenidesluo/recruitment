package com.eumenides.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.eumenides.beans.ResumePartName;
import com.eumenides.compont.StatusCode;
import com.eumenides.entity.ReEducation;
import com.eumenides.entity.Resume;
import com.eumenides.service.GroupService;
import com.eumenides.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;


@Controller
@RequestMapping(value = "/resume")
public class ResumeController {

    @Autowired
    ResumeService resumeService;
    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/init")
    @ResponseBody
    public Map<String, Object> init(HttpServletRequest request, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            result.put("status", StatusCode.AUTHENTICATION_FAILED);
            return result;
        }

        List<Resume> list = resumeService.findResume(userId);
        if (list == null) {
            result.put("status", StatusCode.SQL_OP_ERR);
            return result;
        }
        result.put("status", StatusCode.SUCCESS);
        result.put("resumes", list);
        return result;
    }

    @RequestMapping(value = "/addResume")
    @ResponseBody
    public Map<String, Object> addResume(HttpServletRequest request, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            result.put("status", StatusCode.AUTHENTICATION_FAILED);
            return result;
        }//��¼��֤
        String resumeJson = request.getParameter("resumeJson");
        if (resumeJson == null) {
            result.put("status", StatusCode.PARAMETER_ERROR);
            return result;
        }
        Resume resumeEntity = JSON.parseObject(resumeJson, Resume.class);
        if (resumeEntity == null) {
            result.put("status", StatusCode.JSON_PARSE_ERROR);
            return result;
        }
        resumeEntity.setUserid(userId);
        Integer resumeId = resumeService.saveResume(resumeEntity);
        if (resumeId < 1) {
            result.put("status", StatusCode.SQL_OP_ERR);
            return result;
        }
        result.put("status", StatusCode.SUCCESS);
        result.put("resumeId", resumeId);
        return result;
    }


    @RequestMapping(value = "/addPart")
    @ResponseBody
    public Map<String, Object> addPartResume(HttpServletRequest request, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            result.put("status", StatusCode.AUTHENTICATION_FAILED);
            return result;
        }

        String partName = request.getParameter("partName");
        String json = request.getParameter("json");
        String resumeId = request.getParameter("resumeId");

        if (partName == null || json == null || resumeId == null) {
            result.put("status", StatusCode.PARAMETER_ERROR);
            return result;
        }
        Integer returnStr;

        switch (partName) {
            case "education":
                returnStr = resumeService.addResumeInformations("education", json, resumeId);
                if (returnStr != null) {
                    result.put("status", StatusCode.SUCCESS);
                } else {
                    result.put("status", StatusCode.UNKNOW_ERROR);
                }
                break;
            case "internship":
                returnStr = resumeService.addResumeInformations("internship", json, resumeId);
                if (returnStr != null) {
                    result.put("status", StatusCode.SUCCESS);
                } else {
                    result.put("status", StatusCode.UNKNOW_ERROR);
                }
                break;
            case "schoolExp":
                returnStr = resumeService.addResumeInformations("schoolExp", json, resumeId);
                if (returnStr != null) {
                    result.put("status", StatusCode.SUCCESS);
                } else {
                    result.put("status", StatusCode.UNKNOW_ERROR);
                }
                break;
            case "evaluation":
                returnStr = resumeService.addResumeInformations("evaluation", json, resumeId);
                if (returnStr != null) {
                    result.put("status", StatusCode.SUCCESS);
                } else {
                    result.put("status", StatusCode.UNKNOW_ERROR);
                }
                break;
            case "science":
                returnStr = resumeService.addResumeInformations("science", json, resumeId);
                if (returnStr != null) {
                    result.put("status", StatusCode.SUCCESS);
                } else {
                    result.put("status", StatusCode.UNKNOW_ERROR);
                }
                break;

            default:
                result.put("status", StatusCode.PARAMETER_ERROR);
                break;
        }
        return result;
    }


    @RequestMapping(value = "/queryResume")
    @ResponseBody
    public Map<String, Object> queryResume(HttpServletRequest request, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            result.put("status", StatusCode.AUTHENTICATION_FAILED);
            return result;
        }
        String resumeId = request.getParameter("resumeId");
        if (resumeId == null) {
            result.put("status", StatusCode.PARAMETER_ERROR);
            return result;
        }
        Resume resumeEntity = resumeService.queryResume(Integer.parseInt(resumeId));
        if (resumeEntity == null) {
            result.put("status", StatusCode.SQL_OP_ERR);
            return result;
        } else {
            result.put("status", StatusCode.SUCCESS);
            result.put("resume", resumeEntity);
            return result;
        }
    }

    @RequestMapping("/queryDefaultResume")
    @ResponseBody
    public Map<String, Object> queryDefaultResume(Integer queryUserId, Integer groupId, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            result.put("status", StatusCode.AUTHENTICATION_FAILED);
            return result;
        }
        Resume resume = groupService.getDefaultResume(queryUserId, groupId);
        List reEducations = resumeService.queryPartByResumeId(ResumePartName.education.getValue(), resume.getResumeid());
        List evaluation = resumeService.queryPartByResumeId(ResumePartName.evaluation.getValue(), resume.getResumeid());
        List internship = resumeService.queryPartByResumeId(ResumePartName.internship.getValue(), resume.getResumeid());
        List schoolExp = resumeService.queryPartByResumeId(ResumePartName.schoolExp.getValue(), resume.getResumeid());
        List science = resumeService.queryPartByResumeId(ResumePartName.science.getValue(), resume.getResumeid());
        result.put("status", StatusCode.SUCCESS);
        result.put("resume", resume);
        result.put("education", reEducations);
        result.put("evaluation", evaluation);
        result.put("internship", internship);
        result.put("schoolExp", schoolExp);
        result.put("science", science);
        return result;
    }

    @RequestMapping(value = "/queryPart")
    @ResponseBody
    public Map<String, Object> queryPart(HttpServletRequest request, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            result.put("status", StatusCode.AUTHENTICATION_FAILED);
            return result;
        }

        String Id = request.getParameter("Id");
        String isResumeId = request.getParameter("isResumeId");
        String partName = request.getParameter("partName");

        if (isResumeId == null || Id == null || partName == null) {
            result.put("status", StatusCode.PARAMETER_ERROR);
            return result;
        }
        if (isResumeId.equals("1")) {
            List<?> list = resumeService.queryPartByResumeId(partName, Integer.parseInt(Id));
            result.put("status", StatusCode.SUCCESS);
            result.put("result", list);
            return result;
        } else if (isResumeId.equals("0")) {
            Object object = resumeService.queryPartByMainId(partName, Integer.parseInt(Id));
            result.put("status", StatusCode.SUCCESS);
            result.put("result", object);
            return result;
        } else {
            result.put("status", StatusCode.PARAMETER_ERROR);
            return result;
        }

    }


    @RequestMapping(value = "/deleteResume")
    @ResponseBody
    public Map<String, Object> deleteResume(HttpServletRequest request, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            result.put("status", StatusCode.AUTHENTICATION_FAILED);
            return result;
        }

        String resumeId = request.getParameter("resumeId");
        if (resumeId == null) {
            result.put("status", StatusCode.PARAMETER_ERROR);
            return result;
        }
        if (resumeService.deleteResume(Integer.parseInt(resumeId))) {
            result.put("status", StatusCode.SUCCESS);
            return result;
        } else {
            result.put("status", StatusCode.UNKNOW_ERROR);
            return result;
        }
    }


    @RequestMapping(value = "/deletePart")
    @ResponseBody
    public Map<String, Object> deletePart(HttpServletRequest request, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            result.put("status", StatusCode.AUTHENTICATION_FAILED);
            return result;
        }

        String partName = request.getParameter("partName");
        String id = request.getParameter("Id");
        if (partName == null || id == null) {
            result.put("status", StatusCode.PARAMETER_ERROR);
            return result;
        }

        if (resumeService.deletePartById(Integer.parseInt(id), partName)) {
            result.put("status", StatusCode.SUCCESS);
            return result;
        }
        result.put("status", StatusCode.UNKNOW_ERROR);
        return result;
    }


    @RequestMapping(value = "/updateParts")
    @ResponseBody
    public Map<String, Object> updateParts(HttpServletRequest request, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            result.put("status", StatusCode.AUTHENTICATION_FAILED);
            return result;
        }//��¼��֤

        String partName = request.getParameter("partName");
        String json = request.getParameter("json");
        if (partName == null || json == null) {
            result.put("status", StatusCode.PARAMETER_ERROR);
            return result;
        }

        if (resumeService.updatePart(json, partName)) {
            result.put("status", StatusCode.SUCCESS);
            return result;
        } else {
            result.put("status", StatusCode.UNKNOW_ERROR);
            return result;
        }

    }
}


