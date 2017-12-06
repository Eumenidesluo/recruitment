package com.eumenides.service.impl;

import com.eumenides.dao.CompanyMapper;
import com.eumenides.entity.Company;
import com.eumenides.entity.CompanyExample;
import com.eumenides.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Eumenides on 2017/10/18.
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyMapper companyMapper;
    @Override
    public List<Company> findCompanyesByKey(String key) {
        return null;
    }
}
