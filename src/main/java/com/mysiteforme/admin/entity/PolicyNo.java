package com.mysiteforme.admin.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Description 保单号实体
 * @date  2019年3月21日下午11:50:57
 * @version V1.0  
 * @author 邹立强   (zoulq@cloud-young.com)
 * <p>Copyright (c) Department of Research and Development/Beijing.</p>
 */
@TableName("policy_no")
public class PolicyNo implements java.io.Serializable {

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
     * NO.初始值
     */
    @TableField("start_no")
    private Integer startNo;
    
    /**
     * NO.总数量
     */
    @TableField("no_total")
    private Integer noTotal;

    /**
     * NO.已分配数量
     */
    @TableField("allocation_number")
    private Integer allocationNumber;
    
    /**
     * NO.当前保单数量
     */
    @TableField("no_number")
    private Integer noNumber; 
    
    /**
     * 最新NO.
     */
    @TableField("new_no")
    private Integer newNo; 

    /**
     * 启用状态
     */
    @TableField("enable_state")
    private Integer enableState;

    /**
     * 创建时间
     */
    @TableField("create_date")
    private Date createDate;

    /**
     * 创建用户Id
     */
    @TableField("create_by")
    private Long createBy;

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

    public Integer getNoTotal() {
        return noTotal;
    }

    public void setNoTotal(Integer noTotal) {
        this.noTotal = noTotal;
    }

    public Integer getAllocationNumber() {
        return allocationNumber;
    }

    public void setAllocationNumber(Integer allocationNumber) {
        this.allocationNumber = allocationNumber;
    }

    public Integer getEnableState() {
        return enableState;
    }

    public void setEnableState(Integer enableState) {
        this.enableState = enableState;
    }
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getNoNumber() {
        return noNumber;
    }

    public void setNoNumber(Integer noNumber) {
        this.noNumber = noNumber;
    }

    public Integer getNewNo() {
        return newNo;
    }

    public void setNewNo(Integer newNo) {
        this.newNo = newNo;
    }

    public Integer getStartNo() {
        return startNo;
    }

    public void setStartNo(Integer startNo) {
        this.startNo = startNo;
    }
}
