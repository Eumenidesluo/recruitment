package com.eumenides.dao;

import com.eumenides.entity.ReEvaluation;
import com.eumenides.entity.ReEvaluationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReEvaluationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_evaluation
     *
     * @mbggenerated
     */
    int countByExample(ReEvaluationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_evaluation
     *
     * @mbggenerated
     */
    int deleteByExample(ReEvaluationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_evaluation
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer resumeid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_evaluation
     *
     * @mbggenerated
     */
    int insert(ReEvaluation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_evaluation
     *
     * @mbggenerated
     */
    int insertSelective(ReEvaluation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_evaluation
     *
     * @mbggenerated
     */
    List<ReEvaluation> selectByExample(ReEvaluationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_evaluation
     *
     * @mbggenerated
     */
    ReEvaluation selectByPrimaryKey(Integer resumeid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_evaluation
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") ReEvaluation record, @Param("example") ReEvaluationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_evaluation
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") ReEvaluation record, @Param("example") ReEvaluationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_evaluation
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ReEvaluation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table re_evaluation
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ReEvaluation record);
}