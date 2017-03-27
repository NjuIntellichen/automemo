package com.intellichens.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author raychen, cheney
 * @date 2017/3/23
 * @version V1.0
 */
@Entity
@Table(name = "my_group")
public class GroupModel {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "group_avatar")
    private String groupAvatar;

    @Column(name = "leader_id")
    private int leaderId;

    private Integer people;

    private Integer record;

    private String description;

    @Column(name = "create_at")
    private Date createAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupAvatar() {
        return groupAvatar;
    }

    public void setGroupAvatar(String groupAvatar) {
        this.groupAvatar = groupAvatar;
    }

    public int getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(int leaderId) {
        this.leaderId = leaderId;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public Integer getRecord() {
        return record;
    }

    public void setRecord(Integer record) {
        this.record = record;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getGroupId() {

        return groupId;
    }

    public GroupModel() {
    }
}
