package com.eumenides.service;

import com.eumenides.entity.Resume;

import java.util.List;



public interface ResumeService {
	public Integer addResumeInformations(String partName,String json,Object... values);
	public Resume queryResume(int resumeId);
	public List<?> queryPartByResumeId(String partName,int resumeId);
	public Object queryPartByMainId(String partName,int Id);
	public Boolean deleteResume(int resumeId);
	public Boolean deletePartById(int Id,String partName);
	public Boolean updatePart(String json,String partName);
	List<Resume> findResume(int userId);
	int saveResume(Resume resume);
}
