package com.intellichens.service.impl;

import com.intellichens.dao.UserDAO;
import com.intellichens.model.UserModel;
import com.intellichens.service.LoginService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by raychen on 2017/3/21.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public int login(String phone, String pwd) {
        List<UserModel> users = userDAO.findByPhone(phone);
        if (users == null || users.size() == 0){
            return 0;
        }
        UserModel user = users.get(0);
        if (user.getPassword().equals(pwd)){
            return user.getId();
        }
        return -1;
    }

    @Override
    public int register(String phone, String pwd) {
        List<UserModel> users = userDAO.findByPhone(phone);
        if (users == null || users.size() == 0){
            UserModel user = new UserModel();
            user.setPhone(phone);
            user.setPassword(pwd);
            userDAO.saveAndFlush(user);
            return 1;
        }
        return -1;
    }
}
