package com.eumenides.entity;

import java.util.Date;

public class Notice {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.noticeId
     *
     * @mbggenerated
     */
    private Integer noticeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.userId
     *
     * @mbggenerated
     */
    private Integer userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.notice
     *
     * @mbggenerated
     */
    private String notice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.time
     *
     * @mbggenerated
     */
    private Date time;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.type
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.noticeId
     *
     * @return the value of notice.noticeId
     *
     * @mbggenerated
     */
    public Integer getNoticeid() {
        return noticeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.noticeId
     *
     * @param noticeid the value for notice.noticeId
     *
     * @mbggenerated
     */
    public void setNoticeid(Integer noticeid) {
        this.noticeid = noticeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.userId
     *
     * @return the value of notice.userId
     *
     * @mbggenerated
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.userId
     *
     * @param userid the value for notice.userId
     *
     * @mbggenerated
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.notice
     *
     * @return the value of notice.notice
     *
     * @mbggenerated
     */
    public String getNotice() {
        return notice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.notice
     *
     * @param notice the value for notice.notice
     *
     * @mbggenerated
     */
    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.time
     *
     * @return the value of notice.time
     *
     * @mbggenerated
     */
    public Date getTime() {
        return time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.time
     *
     * @param time the value for notice.time
     *
     * @mbggenerated
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.type
     *
     * @return the value of notice.type
     *
     * @mbggenerated
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.type
     *
     * @param type the value for notice.type
     *
     * @mbggenerated
     */
    public void setType(Integer type) {
        this.type = type;
    }
}