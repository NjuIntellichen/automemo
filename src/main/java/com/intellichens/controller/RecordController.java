package com.intellichens.controller;

import com.intellichens.model.RecordModel;
import com.intellichens.model.TagModel;
import com.intellichens.service.RecordService;
import com.intellichens.util.ResultUtil;
import com.intellichens.util.model_util.RecordUtil;
import com.intellichens.util.model_util.TagUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raychen, cheney
 * @date 2017/3/22
 * @version V1.0
 */
@RestController
@RequestMapping("/record/")
public class RecordController {
    private final RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @RequestMapping(value = "ready")
    @ResponseBody
    public JSONObject readyRecord(@SessionAttribute Integer user, Integer gid){
        int recordId = recordService.createRecord(user,gid);
        return ResultUtil.wrapResult(recordId);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject createRecord(@RequestParam("gid") Integer gid,
                                   @RequestParam("content") String text,
                                   HttpSession session){
        Integer uid = (Integer) session.getAttribute("user");
        int res = recordService.createRecord(uid, gid, text);
        return ResultUtil.wrapResult(res);
    }

    @RequestMapping(value = "group/{gid}", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray getGroupRecords(@PathVariable("gid") Integer gid){
        List<RecordModel> records = recordService.getGroupRecords(gid);
        JSONArray ret = new JSONArray();
        for (RecordModel record: records) {
            ret.add(RecordUtil.convertRecord(record));
        }
        return ret;
    }

    @RequestMapping(value = "user/{uid}", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray getUserRecords(@PathVariable("uid") Integer uid){
        List<RecordModel> records = recordService.getUserRecords(uid);
        JSONArray ret = new JSONArray();
        for (RecordModel record: records) {
            ret.add(RecordUtil.convertRecord(record));
        }
        return ret;
    }

    @RequestMapping(value = "{rid}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getRecord(@PathVariable("rid") Integer rid){
        RecordModel record = recordService.getRecord(rid);
        return RecordUtil.convertRecord(record);
    }

    @RequestMapping(value = "{rid}/tags", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addTags(@PathVariable("rid") Integer rid,
                              @RequestParam("tags") String tags){
        String[] tagArr = tags.split("#");
        List<String> tagList = new ArrayList<String>();
        for (int i = 0; i < tagArr.length; i++) {
            tagList.add(tagArr[i]);
        }
        int res = recordService.createTags(rid, tagList);
        return ResultUtil.wrapResult(res);
    }

    @RequestMapping(value = "{rid}/drop", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject dropRecord(@PathVariable("rid") Integer rid){
        int res = recordService.removeRecord(rid);
        return ResultUtil.wrapResult(res);
    }

    @RequestMapping(value = "{rid}/update", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateRecord(@PathVariable("rid") Integer rid,
                                   @RequestParam("text") String text,
                                   @RequestParam("title") String title){
        int res = recordService.updateRecord(rid, text, title);
        return ResultUtil.wrapResult(res);
    }

    @RequestMapping(value = "{rid}/tags", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray getTags(@PathVariable("rid") Integer rid){
        List<TagModel> tags = recordService.getTags(rid);
        JSONArray ret = new JSONArray();
        for (TagModel tag: tags) {
            ret.add(TagUtil.convertRecord(tag));
        }
        return ret;
    }
}
