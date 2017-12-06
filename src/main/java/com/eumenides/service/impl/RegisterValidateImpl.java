package com.eumenides.service.impl;

import java.util.Date;
import java.util.List;

import com.eumenides.compont.SHA256Util;
import com.eumenides.compont.ServiceException;
import com.eumenides.dao.NoticeMapper;
import com.eumenides.dao.UserMapper;
import com.eumenides.entity.Notice;
import com.eumenides.entity.User;
import com.eumenides.entity.UserExample;
import com.eumenides.service.RegisterValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;


/**
 * Created by Eumenides on 2017/2/18.
 */
@Service("registerValidate")
public class RegisterValidateImpl implements RegisterValidate {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NoticeMapper noticeMapper;

    public void processregister(String password, String email) {
        User user = new User();
        user.setPassword(password);
        user.setEmail(email);
        user.setRegistertime(new java.sql.Timestamp(System.currentTimeMillis()));
        user.setStatus(0);
        user.setValidatecode(SHA256Util.getSHA256StrJava(email));

        userMapper.insertSelective(user);
        StringBuffer sb = new StringBuffer("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
        sb.append("<a href=\"http://localhost:8082/OutsourcingTalent/register?action=activate&email=");
        sb.append(email);
        sb.append("&validateCode=");
        sb.append(user.getValidatecode());
        sb.append("\">http://localhost:8082/OutsourcingTalent/register?action=activate&email=");
        sb.append(email);
        sb.append("&validateCode=");
        sb.append(user.getValidatecode());
        sb.append("</a>");


        SendEmailImpl sendEmail = (SendEmailImpl) SpringContextHolder.getBean("sendEmail");
        sendEmail.send(email, sb.toString());

    }

    public void processActivate(String email, String validateCode) throws ServiceException, ParseException {
        UserExample example=new UserExample();
        example.or().andEmailEqualTo(email);
        List<User> users = userMapper.selectByExample(example);
        User user=null;
        if (users!=null&&!users.isEmpty())
            user=users.get(0);
        if (user != null) {
            if (user.getStatus() == 0) {
                Date currentTime = new Date();
                currentTime.before(user.getRegistertime());
                if (validateCode.equals(user.getValidatecode())) {
                    System.out.println("==sq===" + user.getStatus());
                    user.setStatus(1);
                    System.out.println("==sh===" + user.getStatus());
                    userMapper.updateByPrimaryKeySelective(user);
                    Notice noticeEntity = new Notice();
                    noticeEntity.setNotice("激活成功");
                    noticeEntity.setUserid(user.getId());
                    noticeMapper.insertSelective(noticeEntity);
                } else {
                    throw new ServiceException("激活码不正确");
                }
            } else {
                throw new ServiceException("激活码已过期！");
            }
        } else {
            throw new ServiceException("邮箱已激活，请登录！");
        }
    }
}
