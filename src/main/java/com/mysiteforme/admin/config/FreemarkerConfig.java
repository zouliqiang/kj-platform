package com.mysiteforme.admin.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mysiteforme.admin.freemark.SysUserTempletModel;

import freemarker.template.Configuration;

/**
 * Created by wangl on 2017/11/26.
 * todo:
 */
@Component
public class FreemarkerConfig {

    @Autowired
    private Configuration configuration;

    @Autowired
    private SysUserTempletModel sysUserTempletModel;

    @PostConstruct
    public void setSharedVariable() {
        //获取系统用户信息
        configuration.setSharedVariable("sysuser",sysUserTempletModel);
    }
}
