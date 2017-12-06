package com.eumenides.service;

import com.eumenides.beans.RecruitBean;

import java.util.List;



public interface RecruitService {
	
	public List<RecruitBean> findRecruitsLimit(int begin, int max, String tag);
	public List<RecruitBean> findByKey(String key);
	public RecruitBean findRecruitByRecruitId(int recruitId);
}
