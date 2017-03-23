package com.intellichens.service;

import com.intellichens.model.GroupModel;

import java.util.List;

/**
 * Created by raychen on 2017/3/22.
 */
public interface GroupService {
    int applyGroup(Integer userId, Integer groupId);
    List<GroupModel> getGroups(Integer userId);
    int createGroup(Integer userId, String groupName, String description, String groupAvatar);
    int updateGroup(Integer groupId, String groupName, String description, String groupAvatar);
    int dropGroup(Integer userId, Integer groupId);
    int doApply(Integer userId, Integer groupId, boolean accept);
}
