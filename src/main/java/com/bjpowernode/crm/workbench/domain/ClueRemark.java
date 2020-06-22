package com.bjpowernode.crm.workbench.domain;

/**
 * lzx
 * 2020/3/10
 */
public class ClueRemark {


    private String id;  //主键
    private String noteContent; //备注信息
    private String createTime;  //创建时间
    private String createBy;    //创建人
    private String editTime;    //修改时间
    private String editBy;  //修改人
    private String editFlag;    //是否已经修改过的标记
    private String clueId;  //线索id 外键 ---- 关联 tbl_clue

    public String getId() {
        return id;
    }

    public ClueRemark setId(String id) {
        this.id = id;
        return this;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public ClueRemark setNoteContent(String noteContent) {
        this.noteContent = noteContent;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public ClueRemark setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getCreateBy() {
        return createBy;
    }

    public ClueRemark setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public String getEditTime() {
        return editTime;
    }

    public ClueRemark setEditTime(String editTime) {
        this.editTime = editTime;
        return this;
    }

    public String getEditBy() {
        return editBy;
    }

    public ClueRemark setEditBy(String editBy) {
        this.editBy = editBy;
        return this;
    }

    public String getEditFlag() {
        return editFlag;
    }

    public ClueRemark setEditFlag(String editFlag) {
        this.editFlag = editFlag;
        return this;
    }

    public String getClueId() {
        return clueId;
    }

    public ClueRemark setClueId(String clueId) {
        this.clueId = clueId;
        return this;
    }
}
