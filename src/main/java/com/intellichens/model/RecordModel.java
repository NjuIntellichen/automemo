package com.intellichens.model;

import com.intellichens.util.RecordState;

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
@Table(name = "record")
public class RecordModel {
    @Id
    @GeneratedValue
    private int id;

    private String content;

    @Enumerated(EnumType.STRING)
    private RecordState state;

    @Column(name = "create_at")
    private Date createAt;

    private String topic;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public RecordState getState() {
        return state;
    }

    public void setState(RecordState state) {
        this.state = state;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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
