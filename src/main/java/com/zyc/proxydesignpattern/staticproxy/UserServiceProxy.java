package com.zyc.proxydesignpattern.staticproxy;

public class UserServiceProxy implements UserService {

    private UserService userService;

    public UserServiceProxy(UserService userService){
        this.userService = userService;
    }

    @Override
    public String login(String username, String password) {
        System.out.println("---前置---");
        String result = userService.login(username,password);
        System.out.println("---后置---");
        return result;
    }
}
