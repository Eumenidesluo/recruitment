package com.eumenides.service;

import java.util.List;
import com.eumenides.entity.CollectUserRecruit;

public interface CollectRecruitService {

	public Integer addCollect(CollectUserRecruit entity);
	public Boolean deleteCollect(Integer userId,Integer recruitId) ;
	public void updateCollect(CollectUserRecruit entity);
	public List<?> findCollectsByUserId(int userId);
	public List<?> findCollectsByRecruitId(int recruitId);
	public CollectUserRecruit findCollectOne(Integer userId, Integer recruitId);
}
