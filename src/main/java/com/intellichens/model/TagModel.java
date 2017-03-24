package com.intellichens.model;

import javax.persistence.*;
import java.sql.Date;

/**
 *
 * @author raychen,cheney
 * @date 2017/3/23
 * @version V1.0
 */
@Entity
@Table(name = "tag")
public class TagModel {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "tag_name")
    private String tagName;

    private String info;

    private Integer state;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "record_id")
    private int recordId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }
}
