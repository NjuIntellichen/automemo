package com.intellichens.service;

import com.intellichens.model.RecordModel;
import com.intellichens.model.TagModel;

import java.util.List;

/**
 * Created by raychen on 2017/3/22.
 */
public interface RecordService {
    int createRecord(Integer userId, Integer groupId, String text);
    int createRecord(Integer userId,Integer groupId);
    int updateRecord(Integer recordId, String text, String topic);
    int removeRecord(Integer recordId);
    int createTags(Integer recordId, List<String> tags);
    List<RecordModel> getUserRecords(Integer userId);
    List<RecordModel> getGroupRecords(Integer groupId);
    RecordModel getRecord(Integer groupId);
    List<TagModel> getTags(Integer recordId);
}
