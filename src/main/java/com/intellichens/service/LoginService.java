package com.intellichens.service;
/**
 * Created by raychen on 2017/3/21.
 */
public interface LoginService {
    int login(String phone, String pwd);
    int register(String phone, String pwd);
}
