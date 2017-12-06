package com.eumenides.service.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.eumenides.dao.PersonalInfMapper;
import com.eumenides.dao.UserMapper;
import com.eumenides.entity.PersonalInf;
import com.eumenides.entity.PersonalInfExample;
import com.eumenides.entity.User;
import com.eumenides.service.PersonalInfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("personalInfService")
public class PersonalInfServiceImpl implements PersonalInfService {

    @Autowired
    PersonalInfMapper personalInfMapper;
    @Autowired
    UserMapper userMapper;

    public PersonalInf getPersonByEmail(String email) {
        PersonalInfExample example = new PersonalInfExample();
        example.or().andEmailEqualTo(email);
        List<PersonalInf> entities = personalInfMapper.selectByExample(example);
        if (entities == null || entities.isEmpty())
            return null;
        return entities.get(0);
    }

    public Boolean updatePersonInformation(Map<String, String> informations) {
        PersonalInf entity = getPersonByEmail(informations.get("email"));
        if (entity == null) {
            return false;
        }
        generateEntity(entity, informations);

        return true;
    }

    private void generateEntity(PersonalInf entity, Map<String, String> informations) {
        entity.setName(informations.get("name"));
        entity.setAddress(informations.get("address"));
        entity.setEducation(informations.get("education"));
        entity.setMajor(informations.get("major"));
        entity.setPhonenumber(informations.get("phone"));
        entity.setAddress(informations.get("sex"));
        entity.setSchool(informations.get("school"));
        entity.setGraduationtime(informations.get("graduationTime"));
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date date = bartDateFormat.parse(informations.get("birthday"));
            java.sql.Date sqldate = new Date(date.getTime());
            entity.setBirthday(sqldate);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public Boolean updatePersonInformation(PersonalInf entity) {
        if(personalInfMapper.updateByPrimaryKeySelective(entity)!=1)
            return false;
        return true;
    }

    @Override
    public PersonalInf getPersonById(String id) {
        User userEntity = userMapper.selectByPrimaryKey(Integer.parseInt(id));
        if (userEntity == null) {
            return null;
        }
        PersonalInfExample example=new PersonalInfExample();
        example.or().andEmailEqualTo(userEntity.getEmail());
        List<PersonalInf> personalInfEntities = personalInfMapper.selectByExample(example);
        if (personalInfEntities==null||personalInfEntities.isEmpty())
            return null;
        return personalInfEntities.get(0);
    }


}
