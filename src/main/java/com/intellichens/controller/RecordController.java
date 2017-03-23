package com.intellichens.controller;

import com.intellichens.service.RecordService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by raychen on 2017/3/22.
 */
@RestController
@RequestMapping("/record/")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public JSONObject createRecord(@RequestParam("gid") Integer gid,
                                   @RequestParam("content") String text,
                                   HttpSession session){
        JSONObject ret = new JSONObject();
        Integer uid = (Integer) session.getAttribute("user");
        int res = recordService.createRecord(uid, gid, text);
        ret.put("res", res);
        return ret;
    }

}
