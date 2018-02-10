package com.zyc.proxydesignpattern.inaction.controller;

import com.zyc.proxydesignpattern.inaction.entity.Project;
import com.zyc.proxydesignpattern.inaction.service.ProjectService;
import com.zyc.proxydesignpattern.inaction.util.RedisUtil;
import com.zyc.proxydesignpattern.inaction.util.proxy.DataBaseProxyHandler;
import com.zyc.proxydesignpattern.inaction.util.proxy.ProxyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProjectController {


    @Autowired
    ProjectService projectService;


    @Autowired
    RedisUtil redisUtil;


    public Project getProjectById(String id){
        //在这里用了个匿名内部类，就不在外边创建新的Proxy了。
        //在这里可以更加直观的看，
        return new DataBaseProxyHandler<ProjectService>(redisUtil).proxy(projectService, new ProxyInterface() {
            //在doBegin方法中是 getProjectById 前做的事情。
            //从 DataBaseProxyHandler 的invoke 方法，可以看到 如果返回的是null才会去运行 getProjectById
            //如果不为null  则直接返回了。
            @Override
            public Object doBegin(Object o, Object[] param) {
                RedisUtil redisUtil = (RedisUtil)o;

                return redisUtil.getProjectById(id);
            }

            //在doEnd方法中 是 getProjectById 后做的事情
            //在 getProjectById 中 doEnd是不用做任何事的。
            //但是 如果是 saveProject 呢？ 我们可以在doBegin中不做任何事情，
            //在doEnd中可以 判断如果saveProject插入到 mysql/oracle 那么在doEnd可以插入到缓存中。
            @Override
            public Object doEnd(Object returnObj, Object o, Object[] param) {
                return null;
            }
        }).getProjectById(id);
    }
}
