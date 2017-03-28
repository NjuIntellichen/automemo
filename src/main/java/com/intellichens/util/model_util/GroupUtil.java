package com.intellichens.util.model_util;

import com.intellichens.model.GroupModel;
import org.json.simple.JSONObject;

/**
 * Created by raychen on 2017/3/27.
 */
public class GroupUtil {
    public static JSONObject convertRecord(GroupModel group){
        JSONObject obj = new JSONObject();
        obj.put("gid", group.getId());
        obj.put("group_name", group.getGroupName());
        obj.put("group_id", group.getGroupId());
        obj.put("leader_id", group.getLeaderId());
        obj.put("people", group.getPeople());
        obj.put("records", group.getRecord());
        obj.put("depict", group.getDescription());
        obj.put("time", group.getCreateAt());
        return obj;
    }
}
