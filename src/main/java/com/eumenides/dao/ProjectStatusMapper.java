package com.eumenides.dao;

import com.eumenides.entity.ProjectStatus;
import com.eumenides.entity.ProjectStatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectStatusMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_status
     *
     * @mbggenerated
     */
    int countByExample(ProjectStatusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_status
     *
     * @mbggenerated
     */
    int deleteByExample(ProjectStatusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_status
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer projectid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_status
     *
     * @mbggenerated
     */
    int insert(ProjectStatus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_status
     *
     * @mbggenerated
     */
    int insertSelective(ProjectStatus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_status
     *
     * @mbggenerated
     */
    List<ProjectStatus> selectByExample(ProjectStatusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_status
     *
     * @mbggenerated
     */
    ProjectStatus selectByPrimaryKey(Integer projectid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_status
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") ProjectStatus record, @Param("example") ProjectStatusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_status
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") ProjectStatus record, @Param("example") ProjectStatusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_status
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ProjectStatus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_status
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ProjectStatus record);
}