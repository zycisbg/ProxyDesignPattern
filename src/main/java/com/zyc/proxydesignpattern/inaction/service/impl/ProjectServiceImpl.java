package com.zyc.proxydesignpattern.inaction.service.impl;

import com.zyc.proxydesignpattern.inaction.entity.Project;
import com.zyc.proxydesignpattern.inaction.service.ProjectService;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService{
    public Project getProjectById(String id){
        return new Project("123","假装从数据库中取出的项目");
    }
}
