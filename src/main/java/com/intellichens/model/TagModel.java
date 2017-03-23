package com.intellichens.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by raychen on 2017/3/23.
 */
@Entity
@Table(name = "tag", schema = "automemo_test", catalog = "")
public class TagModel {
    private int id;
    private String tagName;
    private String info;
    private Integer state;
    private Date createAt;
    private RecordModel recordId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "tag_name", nullable = false, length = 255)
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Basic
    @Column(name = "info", nullable = true, length = 255)
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "state", nullable = true)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "create_at", nullable = false)
    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagModel tagModel = (TagModel) o;

        if (id != tagModel.id) return false;
        if (tagName != null ? !tagName.equals(tagModel.tagName) : tagModel.tagName != null) return false;
        if (info != null ? !info.equals(tagModel.info) : tagModel.info != null) return false;
        if (state != null ? !state.equals(tagModel.state) : tagModel.state != null) return false;
        if (createAt != null ? !createAt.equals(tagModel.createAt) : tagModel.createAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (tagName != null ? tagName.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "record_id", referencedColumnName = "id", nullable = false)
    public RecordModel getRecordId() {
        return recordId;
    }

    public void setRecordId(RecordModel recordId) {
        this.recordId = recordId;
    }
}
