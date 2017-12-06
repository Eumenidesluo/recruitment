package com.eumenides.service;


import com.eumenides.entity.PersonalInf;

public interface PersonalInfService {
	public PersonalInf getPersonByEmail(String email);
	public Boolean updatePersonInformation(PersonalInf entity);
	public PersonalInf getPersonById(String id);
}
