package com.eumenides.dao;

import com.eumenides.entity.Groupinfo;
import com.eumenides.entity.GroupinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupinfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    int countByExample(GroupinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    int deleteByExample(GroupinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer recordid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    int insert(Groupinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    int insertSelective(Groupinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    List<Groupinfo> selectByExample(GroupinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    Groupinfo selectByPrimaryKey(Integer recordid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Groupinfo record, @Param("example") GroupinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Groupinfo record, @Param("example") GroupinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Groupinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table groupinfo
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Groupinfo record);
}