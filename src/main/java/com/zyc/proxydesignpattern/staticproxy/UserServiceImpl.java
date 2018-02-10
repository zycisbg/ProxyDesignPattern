package com.zyc.proxydesignpattern.staticproxy;

public class UserServiceImpl implements UserService {
    @Override
    public String login(String username, String password) {
        if("admin".equals(username) && "123".equals(password)){
            System.out.println("登录成功");
            return "loginSuccess";
        }else{
            System.out.println("登录失败");
            return "error";
        }
    }
}
