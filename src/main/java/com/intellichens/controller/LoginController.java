package com.intellichens.controller;

import com.intellichens.service.LoginService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by raychen on 2017/3/21.
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
        JSONObject ret = new JSONObject();
        if (res >= 0){
            session.setAttribute("user", res);
            ret.put("res", 1);
        } else {
            ret.put("res", 0);
        }
        return ret;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject register(@RequestParam("phone") String phone,
                            @RequestParam("pwd") String pwd) {
        int res = loginService.register(phone, pwd);
        JSONObject ret = new JSONObject();
        ret.put("res", res);
        return ret;
    }

    @RequestMapping("/hello")
    public String hello(){
        return "Hello world test!";
    }
}
