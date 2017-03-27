package com.intellichens.controller;

import com.intellichens.model.GroupModel;
import com.intellichens.model.UserGroupModel;
import com.intellichens.service.GroupService;
import com.intellichens.util.ResultUtil;
import com.intellichens.util.TestUtil;
import com.intellichens.util.model_util.ApplyUtil;
import com.intellichens.util.model_util.GroupUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by raychen on 2017/3/22.
 */
@RestController
@RequestMapping("/group/")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject createGroup(@RequestParam("gName") String groupName,
                                  @RequestParam("depict") String description,
                                  @RequestParam("avatar") String groupAvatar,
                                  HttpSession session){
        Integer uid = (Integer) session.getAttribute("user");
        int res = groupService.createGroup(uid, groupName, description, groupAvatar);
        return ResultUtil.wrapResult(res);
    }

    @RequestMapping(value = "search/{gid}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject searchGroup(@PathVariable("gid") Integer gid){
        GroupModel group = groupService.searchGroup(gid);
        return GroupUtil.convertRecord(group);
    }

    @RequestMapping(value = "get/my/{uid}", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray getMyGroup(@PathVariable("uid") Integer uid){
        List<GroupModel> groups = groupService.getCreGroups(uid);
        JSONArray ret = new JSONArray();
        for (GroupModel group: groups) {
            ret.add(GroupUtil.convertRecord(group));
        }
        return ret;
    }

    @RequestMapping(value = "get/join/{uid}", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray getJoinGroup(@PathVariable("uid") Integer uid){
        List<GroupModel> groups = groupService.getJoinGroups(uid);
        JSONArray ret = new JSONArray();
        for (GroupModel group: groups) {
            ret.add(GroupUtil.convertRecord(group));
        }
        return ret;
    }

    @RequestMapping(value = "apply", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject applyGroup(@RequestParam("uid") Integer uid,
                                 @RequestParam("gid") Integer gid){
        int res = groupService.applyGroup(uid, gid);
        return ResultUtil.wrapResult(res);
    }

    @RequestMapping(value = "doApply", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject searchGroup(@RequestParam("aid") Integer aid,
                                  @RequestParam("type") Integer type){
        int res = groupService.doApply(aid, type==1);
        return ResultUtil.wrapResult(res);
    }

    @RequestMapping(value = "drop", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject dropGroup(@RequestParam("uid") Integer uid,
                                  @RequestParam("gid") Integer gid){
        int res = groupService.dropGroup(uid, gid);
        return ResultUtil.wrapResult(res);
    }

    @RequestMapping(value = "get/apply/{gid}", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray getGroupApplies(@PathVariable("gid") Integer gid){
        List<UserGroupModel> applies = groupService.getApplies(gid);
        JSONArray ret = new JSONArray();
        for (UserGroupModel apply: applies) {
            ret.add(ApplyUtil.convertRecord(apply));
        }
        return ret;
    }

}
