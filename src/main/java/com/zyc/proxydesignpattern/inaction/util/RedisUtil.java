package com.zyc.proxydesignpattern.inaction.util;


import com.zyc.proxydesignpattern.inaction.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {
    public Project getProjectById(String id){
        if(id!=null && "123".equals(id)){

         return new Project(id,"假装从缓存中取出的项目");
        }else{
            return null;
        }
    }
}
