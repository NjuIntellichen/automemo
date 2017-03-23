package com.intellichens.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by raychen on 2017/3/23.
 */
@Entity
@Table(name = "group", schema = "automemo_test", catalog = "")
public class GroupModel {
    private int id;
    private String groupName;
    private String groupAvatar;
    private int leaderId;
    private Integer people;
    private Integer record;
    private String description;
    private Date createAt;
    private List<RecordModel> records;
    private List<UserGroupModel> user_groups;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "group_name", nullable = false, length = 255)
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Basic
    @Column(name = "group_avatar", nullable = true, length = 255)
    public String getGroupAvatar() {
        return groupAvatar;
    }

    public void setGroupAvatar(String groupAvatar) {
        this.groupAvatar = groupAvatar;
    }

    @Basic
    @Column(name = "leader_id", nullable = false)
    public int getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(int leaderId) {
        this.leaderId = leaderId;
    }

    @Basic
    @Column(name = "people", nullable = true)
    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    @Basic
    @Column(name = "record", nullable = true)
    public Integer getRecord() {
        return record;
    }

    public void setRecord(Integer record) {
        this.record = record;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

        GroupModel that = (GroupModel) o;

        if (id != that.id) return false;
        if (leaderId != that.leaderId) return false;
        if (groupName != null ? !groupName.equals(that.groupName) : that.groupName != null) return false;
        if (groupAvatar != null ? !groupAvatar.equals(that.groupAvatar) : that.groupAvatar != null) return false;
        if (people != null ? !people.equals(that.people) : that.people != null) return false;
        if (record != null ? !record.equals(that.record) : that.record != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (createAt != null ? !createAt.equals(that.createAt) : that.createAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        result = 31 * result + (groupAvatar != null ? groupAvatar.hashCode() : 0);
        result = 31 * result + leaderId;
        result = 31 * result + (people != null ? people.hashCode() : 0);
        result = 31 * result + (record != null ? record.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "groupId")
    public List<RecordModel> getRecords() {
        return records;
    }

    public void setRecords(List<RecordModel> records) {
        this.records = records;
    }

    @OneToMany(mappedBy = "groupId")
    public List<UserGroupModel> getUser_groups() {
        return user_groups;
    }

    public void setUser_groups(List<UserGroupModel> user_groups) {
        this.user_groups = user_groups;
    }
}
