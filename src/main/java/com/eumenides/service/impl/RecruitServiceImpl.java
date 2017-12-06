package com.eumenides.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.eumenides.beans.RecruitBean;
import com.eumenides.dao.CoRecruitMapper;
import com.eumenides.dao.CompanyMapper;
import com.eumenides.entity.CoRecruit;
import com.eumenides.entity.CoRecruitExample;
import com.eumenides.entity.Company;
import com.eumenides.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("recruitService")
public class RecruitServiceImpl implements RecruitService {

    @Autowired
    CoRecruitMapper coRecruitMapper;
    @Autowired
    CompanyMapper companyMapper;

    @Override
    public List<RecruitBean> findRecruitsLimit(int begin, int max, String tag) {
        CoRecruitExample example = new CoRecruitExample();
        example.or().andLabelEqualTo(tag);
        List<?> entityList = coRecruitMapper.selectByExample(example);
        if (entityList == null) {
            return null;
        }
        List<RecruitBean> beanList = new ArrayList<>();

        for (Object o : entityList) {
            CoRecruit entity = (CoRecruit) o;
            Company companyEntity = companyMapper.selectByPrimaryKey(entity.getCompanyid());
            RecruitBean bean = new RecruitBean(entity, companyEntity.getName());
            beanList.add(bean);
        }
        return beanList;
    }

    @Override
    public List<RecruitBean> findByKey(String key) {
        return null;
    }

    @Override
    public RecruitBean findRecruitByRecruitId(int recruitId) {
        CoRecruit coRecruitEntity = coRecruitMapper.selectByPrimaryKey(recruitId);
        if (coRecruitEntity == null) {
            return null;
        }
        Company companyEntity = companyMapper.selectByPrimaryKey(coRecruitEntity.getCompanyid());
        if (companyEntity == null) {
            return null;
        }
        RecruitBean recruitBean = new RecruitBean(coRecruitEntity, companyEntity.getName());
        return recruitBean;
    }
}
