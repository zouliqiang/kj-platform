package com.mysiteforme.admin.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Description 保单实体
 * @date  2019年3月16日下午3:15:41
 * @version V1.0  
 * @author 邹立强   (zoulq@cloud-young.com)
 * <p>Copyright (c) Department of Research and Development/Beijing.</p>
 */
@TableName("sales_slip_history")
public class SalesSlipHistory implements java.io.Serializable {

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
     * NO.
     */
    @TableField("no")
    private String no;

    /**
     * 客户类型1个人客户2企业用户
     */
    @TableField("customer_type")
    private Integer customerType;

    /**
     * 客户名字
     */
    @TableField("customer_name")
    private String customerName;

    /**
    * 证件类型1身份证2军官证3组织机构代码证4护照5其他
    */
    @TableField("certificate_type")
    private Integer certificateType;

    /**
     * 证件号码
     */
    @TableField("certificate_no")
    private String certificateNo;

    /**
     * 客户联系电话
     */
    @TableField("customer_mobile")
    private String customerMobile;

    /**
    * 通讯地址
    */
    @TableField("customer_address")
    private String customerAddress;

    /**
     * 车品牌
     */
    @TableField("vehicle_brand")
    private String vehicleBrand;

    /**
     * 车牌号
     */
    @TableField("licence_plate_no")
    private String licencePlateNo;

    /**
     * 车架号
     */
    @TableField("vehicle_frame_no")
    private String vehicleFrameNo;

    /**
     * 发动机号
     */
    @TableField("engine_frame_no")
    private String engineFrameNo;
    
    /**
     * 登记日期
     */
    @TableField("registration_date")
    private Date registrationDate;

    /**
     * 初始车价格
     */
    @TableField("vehicle_price")
    private Integer vehiclePrice;

    /**
     * 安装日期
     */
    @TableField("install_date")
    private Date installDate;

    /**
     * 安装地点
     */
    @TableField("install_address")
    private String installAddress;

    /**
    * 安装人
    */
    @TableField("installser")
    private String installser;

    /**
     * 产品SN码
     */
    @TableField("product_no")
    private String productNo;

    /**
    * 保险期限1~3年
    */
    @TableField("insurance_term")
    private Integer insuranceTerm;

    /**
     * 保险开始时间
     */
    @TableField("insurance_start_date")
    private Date insuranceStartDate;

    /**
     * 保险结束时间
     */
    @TableField("insurance_end_date")
    private Date insuranceEndDate;

    /**
     * 赔偿限额
     */
    @TableField("compensate_price")
    private Integer compensatePrice;

    /**
    * 第一受益人
    */
    @TableField("first_beneficiary")
    private String firstBeneficiary;
    

    /**
     * 登记时间
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
    private Integer delFlag=0;
    
    /**
     * 1新增2修改
     */
    @TableField("type")
    private Integer type;
    
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

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(Integer certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getLicencePlateNo() {
        return licencePlateNo;
    }

    public void setLicencePlateNo(String licencePlateNo) {
        this.licencePlateNo = licencePlateNo;
    }

    public String getVehicleFrameNo() {
        return vehicleFrameNo;
    }

    public void setVehicleFrameNo(String vehicleFrameNo) {
        this.vehicleFrameNo = vehicleFrameNo;
    }

    public Integer getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(Integer vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getInstallDate() {
        return installDate;
    }

    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    public String getInstallAddress() {
        return installAddress;
    }

    public void setInstallAddress(String installAddress) {
        this.installAddress = installAddress;
    }

    public String getInstallser() {
        return installser;
    }

    public void setInstallser(String installser) {
        this.installser = installser;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public Integer getInsuranceTerm() {
        return insuranceTerm;
    }

    public void setInsuranceTerm(Integer insuranceTerm) {
        this.insuranceTerm = insuranceTerm;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getInsuranceStartDate() {
        return insuranceStartDate;
    }

    public void setInsuranceStartDate(Date insuranceStartDate) {
        this.insuranceStartDate = insuranceStartDate;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getInsuranceEndDate() {
        return insuranceEndDate;
    }

    public void setInsuranceEndDate(Date insuranceEndDate) {
        this.insuranceEndDate = insuranceEndDate;
    }

    public Integer getCompensatePrice() {
        return compensatePrice;
    }

    public void setCompensatePrice(Integer compensatePrice) {
        this.compensatePrice = compensatePrice;
    }

    public String getFirstBeneficiary() {
        return firstBeneficiary;
    }

    public void setFirstBeneficiary(String firstBeneficiary) {
        this.firstBeneficiary = firstBeneficiary;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getUpdateDate() {
        return updateDate;
    }
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
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

    public String getEngineFrameNo() {
        return engineFrameNo;
    }

    public void setEngineFrameNo(String engineFrameNo) {
        this.engineFrameNo = engineFrameNo;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
