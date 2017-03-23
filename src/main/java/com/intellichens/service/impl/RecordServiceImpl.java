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
 * Created by raychen on 2017/3/22.
 */
@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private GroupDAO groupDAO;
    @Autowired
    private RecordDAO recordDAO;
    @Autowired
    private TagDAO tagDAO;

    @Override
    public int createRecord(Integer userId, Integer groupId, String text) {
        UserModel userModel = userDAO.findOne(userId);
        if (userModel == null) return -1;
        GroupModel groupModel = groupDAO.findOne(groupId);
        if (groupModel == null) return -1;
        RecordModel recordModel = new RecordModel();
        recordModel.setUserId(userModel);
        recordModel.setGroupId(groupModel);
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
            tagModel.setRecordId(recordModel);
            tagModel.setCreateAt(new Date(Calendar.getInstance().getTimeInMillis()));
            tagDAO.save(tagModel);
        }
        tagDAO.flush();
        return 1;
    }

    @Override
    public List<RecordModel> getRecords(Integer userId) {
        UserModel user = userDAO.findOne(userId);
        return user.getRecords();
    }

    @Override
    public List<RecordModel> getRecords(Integer userId, Integer groupId) {
//        return recordDAO.findRecordsByUserAndGroup(userId, groupId);
        return null;
    }
}
