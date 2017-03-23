package com.intellichens.service.impl;

import com.intellichens.dao.GroupDAO;
import com.intellichens.dao.UserDAO;
import com.intellichens.dao.UserGroupDAO;
import com.intellichens.model.GroupModel;
import com.intellichens.model.UserGroupModel;
import com.intellichens.model.UserModel;
import com.intellichens.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 * Created by raychen on 2017/3/22.
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private GroupDAO groupDAO;
    @Autowired
    private UserGroupDAO userGroupDAO;

    @Override
    public int applyGroup(Integer userId, Integer groupId) {
        GroupModel groupModel = groupDAO.findOne(groupId);
        UserModel userModel = userDAO.findOne(userId);
        //group 不存在
        if (groupModel == null) return -1;
        //user 不存在
        if (userModel == null) return -2;
        UserGroupModel userGroupModel = new UserGroupModel();
        userGroupModel.setGroupId(groupModel);
        userGroupModel.setUserId(userModel);
        //0 - 待审核
        userGroupModel.setState(0);
        userGroupModel.setCreateAt(new Date(Calendar.getInstance().getTimeInMillis()));
        userGroupDAO.saveAndFlush(userGroupModel);
        return 1;
    }

    @Override
    public List<GroupModel> getGroups(Integer userId) {
        if (userId == -1) return groupDAO.findAll();
//        return groupDAO.getGroupsByUserId(userId);
        return null;
    }

    @Override
    public int createGroup(Integer userId, String groupName, String description, String groupAvatar) {
        UserModel user = userDAO.findOne(userId);
        if (user == null) return -1;
        GroupModel groupModel = new GroupModel();
        groupModel.setGroupName(groupName);
        groupModel.setDescription(description);
        groupModel.setGroupAvatar(groupAvatar);
        groupModel.setLeaderId(userId);
        groupModel.setPeople(1);
        groupModel.setRecord(0);
        groupModel.setCreateAt(new Date(Calendar.getInstance().getTimeInMillis()));
        groupDAO.saveAndFlush(groupModel);
        return 1;
    }

    @Override
    public int updateGroup(Integer groupId, String groupName, String description, String groupAvatar) {
        GroupModel group = groupDAO.findOne(groupId);
        if (group == null) return -1;
        if (groupName!=null) group.setGroupName(groupName);
        if (description!=null) group.setDescription(description);
        if (groupAvatar!=null) group.setGroupAvatar(groupAvatar);
        groupDAO.saveAndFlush(group);
        return 1;
    }

    @Override
    public int dropGroup(Integer userId, Integer groupId) {
        UserGroupModel apply = new UserGroupModel();
//                userGroupDAO.findByGroupAndUser(userId, groupId);
        if (apply == null) return -1;
        GroupModel group = apply.getGroupId();
        group.setPeople(group.getPeople()-1);
        groupDAO.saveAndFlush(group);
        userGroupDAO.delete(apply);
        return 1;
    }

    @Override
    public int doApply(Integer userId, Integer groupId, boolean accept) {
        UserGroupModel apply = new UserGroupModel();
//                userGroupDAO.findByGroupAndUser(userId, groupId);
        if (apply == null) return -1;
        if (accept){
            apply.setState(1);
            userGroupDAO.saveAndFlush(apply);
            GroupModel group = apply.getGroupId();
            group.setPeople(group.getPeople()+1);
            groupDAO.saveAndFlush(group);
        } else {
            apply.setState(-1);
            userGroupDAO.saveAndFlush(apply);
        }
        return 1;
    }
}
