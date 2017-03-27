package com.intellichens.util.model_util;

import com.intellichens.model.UserModel;
import org.json.simple.JSONObject;

/**
 * Created by raychen on 2017/3/27.
 */
public class UserUtil {

    public static JSONObject convertRecord(UserModel user) {
        JSONObject obj = new JSONObject();
        obj.put("uid", user.getId());
        obj.put("phone", user.getPhone());
        obj.put("user_name", user.getUserName());
        obj.put("time", user.getCreateAt());
        return obj;
    }
}
