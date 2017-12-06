package com.eumenides.dao;

import com.eumenides.entity.ReInternship;
import com.eumenides.entity.ReInternshipExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReInternshipMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_internship
     *
     * @mbggenerated
     */
    int countByExample(ReInternshipExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_internship
     *
     * @mbggenerated
     */
    int deleteByExample(ReInternshipExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_internship
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer internshipid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_internship
     *
     * @mbggenerated
     */
    int insert(ReInternship record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_internship
     *
     * @mbggenerated
     */
    int insertSelective(ReInternship record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_internship
     *
     * @mbggenerated
     */
    List<ReInternship> selectByExample(ReInternshipExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_internship
     *
     * @mbggenerated
     */
    ReInternship selectByPrimaryKey(Integer internshipid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_internship
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") ReInternship record, @Param("example") ReInternshipExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_internship
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") ReInternship record, @Param("example") ReInternshipExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_internship
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ReInternship record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_internship
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ReInternship record);
}