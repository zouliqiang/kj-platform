package com.mysiteforme.admin.entity.VO;

import com.mysiteforme.admin.entity.SalesSlip;

public class SalesSlipVo extends SalesSlip{

    private static final long serialVersionUID = -8363067474873977379L;
    
    /**
     * 录入者姓名
     */
    private String name;
    
    private String website;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
   
}
