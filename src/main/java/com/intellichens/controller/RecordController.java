package com.intellichens.controller;

import com.intellichens.model.RecordModel;
import com.intellichens.service.RecordService;
import com.intellichens.util.ResultUtil;
import com.intellichens.util.model_util.RecordUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raychen on 2017/3/22.
 */
@RestController
@RequestMapping("/record/")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject createRecord(@RequestParam("gid") Integer gid,
                                   @RequestParam("content") String text,
                                   HttpSession session){
        Integer uid = (Integer) session.getAttribute("user");
        int res = recordService.createRecord(uid, gid, text);
        return ResultUtil.wrapResult(res);
    }

    @RequestMapping(value = "records/group/{gid}", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray getGroupRecords(@PathVariable("gid") Integer gid){
        List<RecordModel> records = recordService.getGroupRecords(gid);
        JSONArray ret = new JSONArray();
        for (RecordModel record: records) {
            ret.add(RecordUtil.convertRecord(record));
        }
        return ret;
    }

    @RequestMapping(value = "records/user/{uid}", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray getUserRecords(@PathVariable("uid") Integer uid){
        List<RecordModel> records = recordService.getUserRecords(uid);
        JSONArray ret = new JSONArray();
        for (RecordModel record: records) {
            ret.add(RecordUtil.convertRecord(record));
        }
        return ret;
    }

    @RequestMapping(value = "record/{rid}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getRecord(@PathVariable("rid") Integer rid){
        RecordModel record = recordService.getRecord(rid);
        return RecordUtil.convertRecord(record);
    }

    @RequestMapping(value = "record/{rid}/tags", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addTags(@PathVariable("rid") Integer rid,
                              @RequestParam("tags") String tags){
        String[] tagArr = tags.split("\\#");
        List<String> tagList = new ArrayList<String>();
        for (int i = 0; i < tagArr.length; i++) {
            tagList.add(tagArr[i]);
        }
        int res = recordService.createTags(rid, tagList);
        return ResultUtil.wrapResult(res);
    }

    @RequestMapping(value = "record/{rid}/drop", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject dropRecord(@PathVariable("rid") Integer rid){
        int res = recordService.removeRecord(rid);
        return ResultUtil.wrapResult(res);
    }
}
