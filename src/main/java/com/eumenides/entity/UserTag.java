package com.eumenides.entity;

public class UserTag {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_tag.user_tag_id
     *
     * @mbggenerated
     */
    private Integer userTagId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_tag.user_id
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_tag.tag
     *
     * @mbggenerated
     */
    private String tag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_tag.user_tag_id
     *
     * @return the value of user_tag.user_tag_id
     *
     * @mbggenerated
     */
    public Integer getUserTagId() {
        return userTagId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_tag.user_tag_id
     *
     * @param userTagId the value for user_tag.user_tag_id
     *
     * @mbggenerated
     */
    public void setUserTagId(Integer userTagId) {
        this.userTagId = userTagId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_tag.user_id
     *
     * @return the value of user_tag.user_id
     *
     * @mbggenerated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_tag.user_id
     *
     * @param userId the value for user_tag.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_tag.tag
     *
     * @return the value of user_tag.tag
     *
     * @mbggenerated
     */
    public String getTag() {
        return tag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_tag.tag
     *
     * @param tag the value for user_tag.tag
     *
     * @mbggenerated
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }
}