package com.eumenides.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.eumenides.dao.*;
import com.eumenides.entity.*;
import com.eumenides.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;


@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    ResumeMapper resumeMapper;
    @Autowired
    ReEducationMapper reEducationMapper;
    @Autowired
    ReEvaluationMapper reEvaluationMapper;
    @Autowired
    ReInternshipMapper reInternshipMapper;
    @Autowired
    ReSchoolMapper reSchoolExpMapper;
    @Autowired
    ReScienceMapper reScienceMapper;


    public Integer addResumeInformations(String partName, String json, Object... values) {

        Integer id = -1;
        switch (partName) {

            case "resume":
                Resume entity = JSON.parseObject(json, Resume.class);
                Integer resumeId = resumeMapper.insert(entity);
                if (resumeId != null) {
                    id = resumeId;
                }
                break;
            case "evaluation":
                ReEvaluation evaluationEntity = JSON.parseObject(json, ReEvaluation.class);
                evaluationEntity.setResumeid((Integer) values[0]);
                Integer eInteger = reEvaluationMapper.insert(evaluationEntity);
                if (eInteger != 0) {
                    id = eInteger;
                }
                break;
            case "education":
                ReEducation educationEntity = JSON.parseObject(json, ReEducation.class);
                Integer tmInteger = reEducationMapper.insert(educationEntity);
                if (tmInteger != 0) {
                    id = tmInteger;
                }
                break;
            case "internship":
                ReInternship internshipEntities = JSON.parseObject(json, ReInternship.class);
                tmInteger = reInternshipMapper.insert(internshipEntities);
                if (tmInteger != 0) {
                    id = tmInteger;
                }
                break;
            case "schoolExp":
                ReSchool schoolExpEntities = JSON.parseObject(json, ReSchool.class);
                tmInteger = reSchoolExpMapper.insert(schoolExpEntities);
                if (tmInteger != 0) {
                    id = tmInteger;
                }
                break;
            case "science":
                ReScience scienceEntities = JSON.parseObject(json, ReScience.class);
                tmInteger = reScienceMapper.insert(scienceEntities);
                if (tmInteger != 0) {
                    id = tmInteger;
                }
                break;
            default:
                break;
        }
        return id;
    }

    public Resume queryResume(int resumeId) {
        Resume resumeEntity = resumeMapper.selectByPrimaryKey(resumeId);
        if (resumeEntity != null) {
            return resumeEntity;
        } else
            return null;
    }

    public List<?> queryPartByResumeId(String partName, int resumeId) {
        List<?> list = new ArrayList<>();
        switch (partName) {
            case "education":
                ReEducationExample educationExample = new ReEducationExample();
                educationExample.or().andResumeidEqualTo(resumeId);
                list = reEducationMapper.selectByExample(educationExample);
                break;
            case "internship":
                ReInternshipExample example = new ReInternshipExample();
                example.or().andResumeidEqualTo(resumeId);
                list = reInternshipMapper.selectByExample(example);
                break;
            case "schoolExp":
                ReSchoolExample schoolExample = new ReSchoolExample();
                schoolExample.or().andResumeidEqualTo(resumeId);
                list = reSchoolExpMapper.selectByExample(schoolExample);
                break;
            case "science":
                ReScienceExample scienceExample = new ReScienceExample();
                scienceExample.or().andResumeidEqualTo(resumeId);
                list = reScienceMapper.selectByExample(scienceExample);
                break;
            case "evaluation":
                List<ReEvaluation> list2 = new ArrayList<>();
                ReEvaluationExample evaluationExample = new ReEvaluationExample();
                evaluationExample.or().andResumeidEqualTo(resumeId);
                List<ReEvaluation> reEvaluationEntity = reEvaluationMapper.selectByExample(evaluationExample);
                if (reEvaluationEntity != null || !reEvaluationEntity.isEmpty()) {
                    list2.add(reEvaluationEntity.get(0));
                }
                return list2;
            default:
                list = null;
                break;
        }
        return list;
    }

    public Object queryPartByMainId(String partName, int Id) {
        Object object;
        switch (partName) {
            case "education":
                object = reEducationMapper.selectByPrimaryKey(Id);
                break;
            case "internship":
                object = reInternshipMapper.selectByPrimaryKey(Id);
                break;
            case "schoolExp":
                object = reSchoolExpMapper.selectByPrimaryKey(Id);
            case "science":
                object = reScienceMapper.selectByPrimaryKey(Id);
                break;
            case "evaluation":
                object = reEducationMapper.selectByPrimaryKey(Id);
                break;
            default:
                object = null;
                break;
        }
        return object;
    }

    public Boolean deleteResume(int resumeId) {
        if (resumeMapper.deleteByPrimaryKey(resumeId) == 1)
            return true;
        return false;
    }

    public Boolean deletePartById(int Id, String partName) {
        int flag = 0;
        switch (partName) {
            case "evaluation":
                flag = reEvaluationMapper.deleteByPrimaryKey(Id);
            case "education":
                ReEducation education = reEducationMapper.selectByPrimaryKey(Id);
                if (education != null)
                    flag = reEducationMapper.deleteByPrimaryKey(education.getEducationid());
            case "internship":
                ReInternship internship = reInternshipMapper.selectByPrimaryKey(Id);
                if (internship != null)
                    flag = reInternshipMapper.deleteByPrimaryKey(internship.getInternshipid());
            case "schoolExp":
                ReSchool school = reSchoolExpMapper.selectByPrimaryKey(Id);
                if (school != null)
                    flag = reSchoolExpMapper.deleteByPrimaryKey(school.getSchoolExpId());
            case "science":
                ReScience science = reScienceMapper.selectByPrimaryKey(Id);
                if (science != null)
                    flag = reScienceMapper.deleteByPrimaryKey(science.getScienceid());
            default:
                flag = 0;
        }
        if (flag == 1)
            return true;
        return false;
    }

    public Boolean updatePart(String json, String partName) {
        int flag;
        switch (partName) {
            case "resume":
                Resume resumeEntity = JSON.parseObject(json, Resume.class);
                if (resumeEntity == null) {
                    return false;
                }
                flag = resumeMapper.updateByPrimaryKeySelective(resumeEntity);
                break;
            case "evaluation":
                ReEvaluation evaluationEntity = JSON.parseObject(json, ReEvaluation.class);
                if (evaluationEntity == null) {
                    return false;
                }
                flag = reEvaluationMapper.updateByPrimaryKeySelective(evaluationEntity);
                break;
            case "education":
                ReEducation educationEntity = JSON.parseObject(json, ReEducation.class);
                if (educationEntity == null) {
                    return false;
                }
                flag = reEducationMapper.updateByPrimaryKeySelective(educationEntity);

                break;
            case "internship":
                ReInternship internshipEntity = JSON.parseObject(json, ReInternship.class);
                if (internshipEntity == null) {
                    return false;
                }
                System.out.println(internshipEntity.getStarttime());
                flag = reInternshipMapper.updateByPrimaryKeySelective(internshipEntity);
                break;
            case "schoolExp":
                ReSchool schoolExpEntity = JSON.parseObject(json, ReSchool.class);
                if (schoolExpEntity == null) {
                    return false;
                }
                flag = reSchoolExpMapper.updateByPrimaryKeySelective(schoolExpEntity);
                break;
            case "science":
                ReScience scienceEntity = JSON.parseObject(json, ReScience.class);
                if (scienceEntity == null) {
                    return false;
                }
                flag=reScienceMapper.updateByPrimaryKeySelective(scienceEntity);
                break;
            default:
                flag=0;
        }
        if (flag == 1)
            return true;
        return false;
    }

    @Override
    public List<Resume> findResume(int userId) {
        ResumeExample example=new ResumeExample();
        example.or().andUseridEqualTo(userId);
        return resumeMapper.selectByExample(example);
    }

    @Override
    public int saveResume(Resume resume) {
        return resumeMapper.insertSelective(resume);
    }
}
