package com.eumenides.service;

import com.eumenides.entity.Company;

import java.util.List;

/**
 * Created by Eumenides on 2017/10/17.
 */
public interface CompanyService {
    List<Company> findCompanyesByKey(String key);
}
