package com.intellichens.controller;

import com.intellichens.model.UserModel;
import com.intellichens.service.LoginService;
import com.intellichens.util.ResultUtil;
import com.intellichens.util.model_util.UserUtil;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 *
 * @author raychen, cheney
 * @date 2017/3/21
 * @version V1.0
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject login(@RequestParam("phone") String phone,
                            @RequestParam("pwd") String pwd,
                            HttpSession session) {
        int res = loginService.login(phone, pwd);
        if (res > 0) session.setAttribute("user", res);
        else {
            return ResultUtil.wrapResult(res);
        }
        UserModel user = loginService.getUser((Integer) session.getAttribute("user"));
        JSONObject userObj = UserUtil.convertRecord(user);
        JSONObject ret = new JSONObject();
        String sessionId = session.getId();
        ret.put("res", 1);
        ret.put("user", userObj);
        ret.put("sessionId", sessionId);
        return ret;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject register(@RequestParam("phone") String phone,
                            @RequestParam("pwd") String pwd) {
        int res = loginService.register(phone, pwd);
        return ResultUtil.wrapResult(res);
    }

    @RequestMapping(value = "/current", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getUser(HttpSession session) {
        UserModel user = loginService.getUser((Integer) session.getAttribute("user"));
        return UserUtil.convertRecord(user);
    }

    @RequestMapping("/hello")
    public String hello(){
        return "Hello world test!";
    }
}
