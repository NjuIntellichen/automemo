package com.intellichens.service.impl;

import com.intellichens.dao.GroupDAO;
import com.intellichens.dao.RecordDAO;
import com.intellichens.dao.TagDAO;
import com.intellichens.dao.UserDAO;
import com.intellichens.model.GroupModel;
import com.intellichens.model.RecordModel;
import com.intellichens.model.TagModel;
import com.intellichens.model.UserModel;
import com.intellichens.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author raychen, cheney
 * @date 2017/3/22
 * @version V1.0
 */
@Service
public class RecordServiceImpl implements RecordService {

    private final UserDAO userDAO;
    private final GroupDAO groupDAO;
    private final RecordDAO recordDAO;
    private final TagDAO tagDAO;

    @Autowired
    public RecordServiceImpl(UserDAO userDAO, GroupDAO groupDAO, RecordDAO recordDAO, TagDAO tagDAO) {
        this.userDAO = userDAO;
        this.groupDAO = groupDAO;
        this.recordDAO = recordDAO;
        this.tagDAO = tagDAO;
    }

    @Override
    public int createRecord(Integer userId, Integer groupId, String text) {
        UserModel userModel = userDAO.findOne(userId);
        if (userModel == null) return -1;
        GroupModel groupModel = groupDAO.findOne(groupId);
        if (groupModel == null) return -1;
        RecordModel recordModel = new RecordModel();
        recordModel.setUserId(userModel.getId());
        recordModel.setGroupId(groupModel.getId());
        recordModel.setState(1);
        recordModel.setContent(text);
        recordModel.setCreateAt(new Date(Calendar.getInstance().getTimeInMillis()));
        recordDAO.saveAndFlush(recordModel);
        return 1;
    }

    @Override
    public int updateRecord(Integer recordId, String text, String topic) {
        RecordModel record = recordDAO.findOne(recordId);
        if (record == null) return -1;
        if (text!=null) record.setContent(text);
        if (topic!=null) record.setTopic(topic);
        recordDAO.saveAndFlush(record);
        return 1;
    }

    @Override
    public int removeRecord(Integer recordId) {
        recordDAO.delete(recordId);
        return 1;
    }

    @Override
    public int createTags(Integer recordId, List<String> tags) {
        RecordModel recordModel = recordDAO.findOne(recordId);
        if (recordModel == null) return -1;
        for (String tag: tags) {
            TagModel tagModel = new TagModel();
            tagModel.setTagName(tag);
            tagModel.setRecordId(recordModel.getId());
            tagModel.setCreateAt(new Date(Calendar.getInstance().getTimeInMillis()));
            tagDAO.save(tagModel);
        }
        tagDAO.flush();
        return 1;
    }

    @Override
    public List<RecordModel> getRecords(Integer userId) {
        UserModel user = userDAO.findOne(userId);
        if(user==null) return null;
        return recordDAO.findByUserId(user.getId());
    }

    @Override
    public List<RecordModel> getRecords(Integer userId, Integer groupId) {
//        return recordDAO.findRecordsByUserAndGroup(userId, groupId);
        return null;
    }
}
