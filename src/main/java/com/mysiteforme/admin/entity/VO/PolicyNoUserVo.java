package com.mysiteforme.admin.entity.VO;

import com.mysiteforme.admin.entity.PolicyNoUser;

public class PolicyNoUserVo extends PolicyNoUser{

    private static final long serialVersionUID = -8363067474873977379L;
    
    /**
     * 录入者姓名
     */
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
