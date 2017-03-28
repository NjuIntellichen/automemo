package com.intellichens.util.model_util;

import com.intellichens.model.RecordModel;
import org.json.simple.JSONObject;

/**
 * Created by raychen on 2017/3/27.
 */
public class RecordUtil {
    public static JSONObject convertRecord(RecordModel record){
        JSONObject obj = new JSONObject();
        obj.put("rid", record.getId());
        obj.put("user_id", record.getUserId());
        obj.put("group_id", record.getGroupId());
        obj.put("content", record.getContent());
        obj.put("time", record.getCreateAt());
        return obj;
    }
}
