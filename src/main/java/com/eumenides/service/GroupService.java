package com.eumenides.service;

import com.eumenides.beans.MemberBean;
import com.eumenides.beans.RelateGroupProjectBean;
import com.eumenides.beans.RelateUserGroupBean;
import com.eumenides.entity.Group;
import com.eumenides.entity.Resume;

import java.util.List;



public interface GroupService {

	public Integer createGroup(Integer creator,String groupName);
	
	public List<MemberBean> queryMembers(Integer groupId);
	
	public Boolean inviteMember(Integer groupId,String email);
	
	public Boolean deleteMenber(Integer groupId,Integer deleteId);
	
	public List<RelateUserGroupBean> getUserGroupInfo(Integer userId);
	
	public List<RelateGroupProjectBean> findRelatesByGroupId(Integer groupId);
	
	public Group findGroupByLeaderId(Integer leader);

	void setDefaultResume(Integer userId,Integer groupId,Integer resumeId);
	Resume getDefaultResume(Integer userId,Integer groupId);
}
