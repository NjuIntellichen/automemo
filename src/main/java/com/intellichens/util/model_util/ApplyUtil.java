package com.intellichens.util.model_util;

import com.intellichens.model.UserGroupModel;
import org.json.simple.JSONObject;

/**
 * Created by raychen on 2017/3/27.
 */
public class ApplyUtil {

    public static JSONObject convertRecord(UserGroupModel apply){
        JSONObject obj = new JSONObject();
        obj.put("aid", apply.getId());
        obj.put("user_id", apply.getUserId());
        obj.put("group_id", apply.getGroupId());
        obj.put("state", apply.getState());
        obj.put("time", apply.getCreateAt());
        return obj;
    }
}
