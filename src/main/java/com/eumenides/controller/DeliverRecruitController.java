package com.eumenides.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.eumenides.compont.StatusCode;
import com.eumenides.service.DeliveryRecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/delivery")
public class DeliverRecruitController {

    @Autowired
    private DeliveryRecruitService deliveryRecruitService;

    @RequestMapping(value = "/deliver")
    @ResponseBody
    public Map<String, Object> deliver(HttpSession session, @RequestParam Integer recruitId, @RequestParam Integer resumeId) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            result.put("status", StatusCode.AUTHENTICATION_FAILED);
            return result;
        }
        if (recruitId == null || resumeId == null) {
            result.put("status", StatusCode.PARAMETER_ERROR);
            return result;

        }
        if (deliveryRecruitService.delivery(recruitId, userId, resumeId)) {
            result.put("status", StatusCode.SUCCESS);
            return result;

        }
        result.put("status", StatusCode.UNKNOW_ERROR);
        return result;

    }

    @RequestMapping(value = "/status")
    @ResponseBody
    public Map<String, Object> hasDeliveried(HttpSession session, @RequestParam Integer recruitId) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            result.put("status", StatusCode.AUTHENTICATION_FAILED);
            return result;
        }
        if (recruitId == null) {
            result.put("status", StatusCode.PARAMETER_ERROR);
            return result;

        }

        if (deliveryRecruitService.hasDeliveried(userId, recruitId)) {
            result.put("status", StatusCode.SUCCESS);
            return result;

        }
        result.put("status", StatusCode.NOT_EXIST);
        return result;

    }
}
