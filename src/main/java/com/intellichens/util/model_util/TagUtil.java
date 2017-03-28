package com.intellichens.util.model_util;

import com.intellichens.model.TagModel;
import org.json.simple.JSONObject;

/**
 * Created by raychen on 2017/3/27.
 */
public class TagUtil {
    public static JSONObject convertRecord(TagModel tag){
        JSONObject obj = new JSONObject();
        obj.put("tid", tag.getId());
        obj.put("tag_name", tag.getTagName());
        obj.put("record_id", tag.getRecordId());
        obj.put("time", tag.getCreateAt());
        return obj;
    }
}
