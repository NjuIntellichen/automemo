package com.intellichens.util;


import org.json.simple.JSONObject;

/**
 * Created by raychen on 2017/3/27.
 */
public class ResultUtil {
    public static JSONObject wrapResult(int result){
        JSONObject obj = new JSONObject();
        obj.put("res", result);
        return obj;
    }
}
