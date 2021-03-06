package com.eumenides.dao;

import com.eumenides.entity.RelateGroupProject;
import com.eumenides.entity.RelateGroupProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RelateGroupProjectMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table relate_group_project
     *
     * @mbggenerated
     */
    int countByExample(RelateGroupProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table relate_group_project
     *
     * @mbggenerated
     */
    int deleteByExample(RelateGroupProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table relate_group_project
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer bidid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table relate_group_project
     *
     * @mbggenerated
     */
    int insert(RelateGroupProject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table relate_group_project
     *
     * @mbggenerated
     */
    int insertSelective(RelateGroupProject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table relate_group_project
     *
     * @mbggenerated
     */
    List<RelateGroupProject> selectByExample(RelateGroupProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table relate_group_project
     *
     * @mbggenerated
     */
    RelateGroupProject selectByPrimaryKey(Integer bidid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table relate_group_project
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") RelateGroupProject record, @Param("example") RelateGroupProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table relate_group_project
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") RelateGroupProject record, @Param("example") RelateGroupProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table relate_group_project
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RelateGroupProject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table relate_group_project
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RelateGroupProject record);
}