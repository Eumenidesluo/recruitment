package com.eumenides.service.impl;

import java.sql.Date;
import java.util.List;

import com.eumenides.dao.NoticeMapper;
import com.eumenides.dao.ResumeMapper;
import com.eumenides.dao.SelectUserRecruitMapper;
import com.eumenides.entity.*;
import com.eumenides.service.DeliveryRecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("DeliveryRecruitService")
public class DeliveryRecruitServiceImpl implements DeliveryRecruitService {

	@Autowired
	SelectUserRecruitMapper selectUserRecruitMapper;
	@Autowired
	NoticeMapper noticeMapper;
	@Autowired
	ResumeMapper resumeMapper;
	
	public Boolean delivery(Integer recruitId,Integer userId,Integer resumeId) {
		ResumeExample example=new ResumeExample();
		example.or().andUseridEqualTo(userId);
		List<Resume> resumeList = resumeMapper.selectByExample(example);
		Boolean flag = false;
		for(Resume entity:resumeList){
			if (entity.getResumeid() == resumeId) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			return false;
		}	
		SelectUserRecruit selectUserRecruitEntity = new SelectUserRecruit();
		selectUserRecruitEntity.setRecruitid(recruitId);
		selectUserRecruitEntity.setUserid(userId);
		selectUserRecruitEntity.setResumeid(resumeId);
		selectUserRecruitEntity.setTime(new Date(System.currentTimeMillis()));
		if (selectUserRecruitMapper.insertSelective(selectUserRecruitEntity) !=1) {
			return false;
		}
		Notice noticeEntity = new Notice();
		noticeEntity.setNotice("Ͷ�ݳɹ�");
		noticeEntity.setUserid(userId);
		noticeEntity.setTime(new Date(System.currentTimeMillis()));
		noticeMapper.insertSelective(noticeEntity);
		return true;
	}
	public Boolean hasDeliveried(Integer userId,Integer recruitId) {
		SelectUserRecruitExample example=new SelectUserRecruitExample();
		SelectUserRecruitExample.Criteria criteria=example.createCriteria();
		criteria.andUseridEqualTo(userId);
		criteria.andRecruitidEqualTo(recruitId);
		List<SelectUserRecruit> entity = selectUserRecruitMapper.selectByExample(example);
		if (entity == null||entity.isEmpty()) {
			return false;
		}
		return true;
	}
}
