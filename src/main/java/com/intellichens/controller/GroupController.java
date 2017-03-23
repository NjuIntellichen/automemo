package com.intellichens.controller;

import com.intellichens.service.GroupService;
import org.json.JSONObject;
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
@RequestMapping("/group/")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public JSONObject createGroup(@RequestParam("gName") String groupName,
                                  @RequestParam("depict") String description,
                                  @RequestParam("avatar") String groupAvatar,
                                  HttpSession session){
        Integer uid = (Integer) session.getAttribute("user");
        JSONObject ret = new JSONObject();
        int res = groupService.createGroup(uid, groupName, description, groupAvatar);
        ret.put("res", res);
        return ret;
    }
}
