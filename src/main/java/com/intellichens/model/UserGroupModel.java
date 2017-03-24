package com.intellichens.model;

import javax.persistence.*;
import java.sql.Date;

/**
 *
 * @author raychen, cheney
 * @date 2017/3/23
 * @version V1.0
 */
@Entity
@Table(name = "user_group")
public class UserGroupModel {
    @Id
    @GeneratedValue
    private int id;

    private Integer state;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "group_id")
    private int groupId;

    @Column(name = "user_id")
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
