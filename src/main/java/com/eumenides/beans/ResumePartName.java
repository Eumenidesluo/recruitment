package com.eumenides.beans;

/**
 * Created by Eumenides on 2017/11/30.
 */
public enum ResumePartName {
    education("education"),
    internship("internship"),
    schoolExp("schoolExp"),
    science("science"),
    evaluation("evaluation");

    ResumePartName(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
