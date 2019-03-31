package com.mysiteforme.admin.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @Description 保单号用户对应关系
 * @date  2019年3月21日下午11:50:57
 * @version V1.0  
 * @author 邹立强   (zoulq@cloud-young.com)
 * <p>Copyright (c) Department of Research and Development/Beijing.</p>
 */
@TableName("policy_no_user")
public class PolicyNoUser implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableField("id")
    private Long id;

    /**
     * 保单号
     */
    @TableField("policy_no")
    private String policyNo;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 用户分配数量
     */
    @TableField("allocation_number")
    private Integer allocationNumber;

    /**
     * 用户使用数量
     */
    @TableField("use_number")
    private Integer useNumber;

    /**
     * 创建时间
     */
    @TableField("create_date")
    private Date createDate;

    /**
     * 创建用户Id
     */
    @TableField("create_by")
    private Long createId;

    /**
     * 更新时间
     */
    @TableField("update_date")
    private Date updateDate;

    /**
     * 更新用户Id
     */
    @TableField("update_by")
    private Long updateId;  

    /**
     * 0未删除1已删除
     */
    @TableField("del_flag")
    private Integer delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAllocationNumber() {
        return allocationNumber;
    }

    public void setAllocationNumber(Integer allocationNumber) {
        this.allocationNumber = allocationNumber;
    }

    public Integer getUseNumber() {
        return useNumber;
    }

    public void setUseNumber(Integer useNumber) {
        this.useNumber = useNumber;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }
}
