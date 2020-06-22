package com.bjpowernode.crm.workbench.domain;

/**
 * lzx
 * 2020/3/11
 */
public class ClueActivityRelation {

    private String id;
    private String clueId;
    private String activityId;

    public String getId() {
        return id;
    }

    public ClueActivityRelation setId(String id) {
        this.id = id;
        return this;
    }

    public String getClueId() {
        return clueId;
    }

    public ClueActivityRelation setClueId(String clueId) {
        this.clueId = clueId;
        return this;
    }

    public String getActivityId() {
        return activityId;
    }

    public ClueActivityRelation setActivityId(String activityId) {
        this.activityId = activityId;
        return this;
    }
}
