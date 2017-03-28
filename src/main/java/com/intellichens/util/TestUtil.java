package com.intellichens.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Created by raychen on 2017/3/27.
 */
public class TestUtil {
    public static JSONObject getTestObj(){
        JSONObject test = new JSONObject();
        test.put("test", "123");
        return test;
    }

    public static JSONArray getTestarr(){
        JSONArray array = new JSONArray();
        array.add(getTestObj());
        return array;
    }
}
