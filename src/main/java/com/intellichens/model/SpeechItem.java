package com.intellichens.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author yuminchen
 * @version V1.0
 * @date 2017/3/28
 */
@Entity
@Table(name = "speech")
public class SpeechItem {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "record_id")
    private int recordId;

    @Column(length = 1023)
    private String content;

    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
