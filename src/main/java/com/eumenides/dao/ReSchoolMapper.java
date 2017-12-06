package com.eumenides.dao;

import com.eumenides.entity.ReSchool;
import com.eumenides.entity.ReSchoolExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReSchoolMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_school
     *
     * @mbggenerated
     */
    int countByExample(ReSchoolExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_school
     *
     * @mbggenerated
     */
    int deleteByExample(ReSchoolExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_school
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer schoolExpId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_school
     *
     * @mbggenerated
     */
    int insert(ReSchool record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_school
     *
     * @mbggenerated
     */
    int insertSelective(ReSchool record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_school
     *
     * @mbggenerated
     */
    List<ReSchool> selectByExample(ReSchoolExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_school
     *
     * @mbggenerated
     */
    ReSchool selectByPrimaryKey(Integer schoolExpId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_school
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") ReSchool record, @Param("example") ReSchoolExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_school
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") ReSchool record, @Param("example") ReSchoolExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_school
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ReSchool record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_school
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ReSchool record);
}