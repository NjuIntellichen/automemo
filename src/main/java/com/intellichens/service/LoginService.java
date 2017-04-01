package com.intellichens.service;

import com.intellichens.model.UserModel;

/**
 * Created by raychen on 2017/3/21.
 */
public interface LoginService {
    UserModel login(String phone, String pwd);
    int register(String phone, String pwd);
    UserModel getUser(Integer uid);
}
