package com.mysiteforme.admin.entity.VO;

/**
 * @Description 保单汇总单导出
 * @date  2019年3月26日下午11:44:26
 * @version V1.0  
 * @author 邹立强   (zoulq@cloud-young.com)
 * <p>Copyright (c) Department of Research and Development/Beijing.</p>
 */
public class SummarySalesSlip implements java.io.Serializable{

    private static final long serialVersionUID = 8864867087900128347L;
    
    private String authorizedOutlets;
    
    private String name;
    
    private Integer num;

    public String getAuthorizedOutlets() {
        return authorizedOutlets;
    }

    public void setAuthorizedOutlets(String authorizedOutlets) {
        this.authorizedOutlets = authorizedOutlets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
    
    
}
