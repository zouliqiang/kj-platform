package com.mysiteforme.admin.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.entity.PolicyNo;
import com.mysiteforme.admin.entity.PolicyNoUser;
import com.mysiteforme.admin.entity.SalesSlip;
import com.mysiteforme.admin.entity.SalesSlipExport;
import com.mysiteforme.admin.entity.User;
import com.mysiteforme.admin.entity.VO.SalesSlipVo;
import com.mysiteforme.admin.entity.VO.SummarySalesSlip;
import com.mysiteforme.admin.util.DateUtil;
import com.mysiteforme.admin.util.LayerData;
import com.mysiteforme.admin.util.RestResponse;
import com.mysiteforme.admin.util.poi.ExportExcelUtil;
import com.mysiteforme.admin.util.poi.ExportExcelWrapper;

/**
 * @Description 保单控制
 * @date  2019年3月31日下午10:08:53
 * @version V1.0  
 * @author 邹立强   (zoulq@cloud-young.com)
 * <p>Copyright (c) Department of Research and Development/Beijing.</p>
 */
@Controller
@RequestMapping("/admin/salesslip")
public class SalesSlipController extends BaseController{
    
    @Autowired
    private  RedisTemplate<String, String> redisTemplate;
    
    /**
     * @Description 进入保单列表页 
     * @return String     
     * @version V1.0
     * @auth    邹立强   (zoulq@cloud-young.com)
     * 2019年3月31日 下午10:09:07
     */
    @GetMapping("list")
    public String list() {
        return "/admin/salesslip/list";
    }
    
    /**
     * @Description 查询保单列表 
     * @param page
     * @param limit
     * @param request
     * @return    
     * @return LayerData<SalesSlipVo>     
     * @version V1.0
     * @auth    邹立强   (zoulq@cloud-young.com)
     * 2019年3月31日 下午10:09:24
     */
    @PostMapping("list")
    @ResponseBody
    public LayerData<SalesSlipVo> list(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "10") Integer limit, ServletRequest request) {
        Map<String,Object> map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<SalesSlipVo> layerData = new LayerData<>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("pageStart", (page - 1) * limit);
        paramMap.put("limit", limit);
        if (!map.isEmpty()) {
            String customeType = (String) map.get("custometype");
            if (StringUtils.isNotBlank(customeType)) {
                paramMap.put("customer_type", customeType);
            }
            String no = (String) map.get("no");
            if (StringUtils.isNotBlank(no)) {
                paramMap.put("no", no);
            }
            String customername = (String) map.get("customername");
            if (StringUtils.isNotBlank(customername)) {
                paramMap.put("customer_name", "%" + customername + "%");
            }
        }
        User currentUser = getCurrentUser();
        if (!currentUser.getIsSuper()) {
            paramMap.put("create_by", currentUser.getId());
        }
        List<SalesSlipVo> list = salesSlipService.getListSalesSlip(paramMap);
        Integer total = salesSlipService.getTotal(paramMap);
        layerData.setData(list);
        layerData.setCount(total);
        return layerData;
    }
    
    /**
     * @Description 进入录入保单页面 
     * @return    
     * @return String     
     * @version V1.0
     * @auth    邹立强   (zoulq@cloud-young.com)
     * 2019年3月31日 下午10:09:38
     */
    @GetMapping("add")
    public String add() {
        return "/admin/salesslip/add";
    }
    
    /**
     * @Description 录入保单 
     * @param salesSlip
     * @return    
     * @return RestResponse     
     * @version V1.0
     * @auth    邹立强   (zoulq@cloud-young.com)
     * 2019年3月31日 下午10:09:56
     */
    @RequiresPermissions("salesslip:add")
    @PostMapping("add")
    @SysLog("录入保单数据")
    @ResponseBody
    public RestResponse add(@RequestBody SalesSlip salesSlip) {
        User currentUser = getCurrentUser();
        if(currentUser.getIsSuper()) {
            return RestResponse.failure("超级管理员没有添加保单权限");
        }
        lock("salesSlip");
        try {
        PolicyNo policyNo = policyNoService.getActivePolicyNo();
        if(policyNo==null) {
            return RestResponse.failure("保单未激活，请联系管理员");
        }
        policyNo.setNoNumber(policyNo.getNoNumber()+1);
        policyNo.setNewNo(policyNo.getNewNo()+1);
        policyNo.setUpdateDate(new Date());
        policyNo.setUpdateId(currentUser.getCreateId());
        Date date = new Date();
        salesSlip.setPolicyNo(policyNo.getPolicyNo());
        salesSlip.setNo(policyNo.getNewNoStr());
        salesSlip.setCreateId(currentUser.getId());
        salesSlip.setCreateDate(date);
        salesSlip.setDelFlag(0);
        salesSlip.setInsuranceEndDate(DateUtil.getNextYear(salesSlip.getInsuranceStartDate(), salesSlip.getInsuranceTerm()));
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("userId", currentUser.getId());
        map.put("policyNo", policyNo.getPolicyNo());
        if ("1".equals(salesSlip.getCustomerType())) {
            salesSlip.setCertificateType(1);
        } else {
            salesSlip.setCertificateType(2);
        }
        PolicyNoUser policyNoUser = policyNoUserService.getPolicyNoUser(map);
        if(policyNoUser==null) {
            return RestResponse.failure("您暂且无录入保单权限！请联系管理员");
        }
        Integer allocationNumber = policyNoUser.getAllocationNumber();
        Integer useNumber = policyNoUser.getUseNumber();
        if(useNumber>=allocationNumber) {
            return RestResponse.failure("录入保单数量超限，不可再次录入");
        }
        policyNoUser.setUseNumber(policyNoUser.getUseNumber()+1);
        salesSlipService.saveSalesSlip(salesSlip);
        policyNoService.updateById(policyNo);
        policyNoUserService.updateById(policyNoUser);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            unLock("salesSlip"); 
        }
        return RestResponse.success();
    }
    
    /**
     * @Description 查看保单内容 
     * @param id
     * @param model
     * @return    
     * @return String     
     * @version V1.0
     * @auth    邹立强   (zoulq@cloud-young.com)
     * 2019年3月31日 下午10:10:12
     */
    @GetMapping("look")
    public String look(Long id, Model model) {
        User currentUser = getCurrentUser();
        if (id == null) {
            model.addAttribute("message", "暂无权限");
            return "/admin/error/404";
        }
        SalesSlip salesSlip = salesSlipService.selectById(id);
        if (id != null && !currentUser.getIsSuper() && currentUser.getId().intValue() != salesSlip.getCreateId().intValue()) {
            model.addAttribute("message", "暂无权限");
            return "/admin/error/404";
        }
        if (salesSlip == null) {
            model.addAttribute("message", "暂无保单信息");
            return "/admin/error/404";
        }
        model.addAttribute("salesSlip", salesSlip);
        return "/admin/salesslip/look";
    }
    
    /**
     * @Description 进入编辑保单页面 
     * @param id
     * @param model
     * @return    
     * @return String     
     * @version V1.0
     * @auth    邹立强   (zoulq@cloud-young.com)
     * 2019年3月31日 下午10:10:23
     */
    @GetMapping("edit")
    public String edit(Long id, Model model) {
        User currentUser = getCurrentUser();
        if (id == null) {
            model.addAttribute("message", "暂无权限");
            return "/admin/error/404";
        }
        SalesSlip salesSlip = salesSlipService.selectById(id);
        if (id != null && !currentUser.getIsSuper() && currentUser.getId().intValue() != salesSlip.getCreateId().intValue()) {
            model.addAttribute("message", "暂无权限");
            return "/admin/error/404";
        }
        if (salesSlip == null) {
            model.addAttribute("message", "暂无保单信息");
            return "/admin/error/404";
        }
        model.addAttribute("salesSlip", salesSlip);
        return "/admin/salesslip/edit";
    }
    
    @RequiresPermissions("salesslip:edit")
    @PostMapping("edit")
    @SysLog("编辑保单数据")
    @ResponseBody
    public RestResponse edit(@RequestBody SalesSlip salesSlip) {
        User currentUser = getCurrentUser();
        try {
        Date date = new Date();
        salesSlip.setUpdateId(currentUser.getId());
        salesSlip.setUpdateDate(date);
        salesSlip.setInsuranceEndDate(DateUtil.getNextYear(salesSlip.getInsuranceStartDate(), salesSlip.getInsuranceTerm()));
        if ("1".equals(salesSlip.getCustomerType())) {
            salesSlip.setCertificateType(1);
        } else {
            salesSlip.setCertificateType(2);
        }
        salesSlip.setInsuranceEndDate(DateUtil.getNextYear(salesSlip.getInsuranceStartDate(), salesSlip.getInsuranceTerm()));
        salesSlipService.updateById(salesSlip);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return RestResponse.success();
    }
    
    /**
     * @Description 进入保单打印页面 
     * @param id
     * @param model
     * @return    
     * @return String     
     * @version V1.0
     * @auth    邹立强   (zoulq@cloud-young.com)
     * 2019年3月31日 下午10:10:37
     */
    @GetMapping("print")
    public String print(Long id, Model model) {
        User currentUser = getCurrentUser();
        if (id == null) {
            model.addAttribute("message", "暂无权限");
            return "/admin/error/404";
        }
        SalesSlip salesSlip = salesSlipService.selectById(id);
        if (salesSlip == null) {
            model.addAttribute("message", "暂无保单信息");
            return "/admin/error/404";
        }
        if (id != null && !currentUser.getIsSuper() && currentUser.getId().intValue() != salesSlip.getCreateId().intValue()) {
            model.addAttribute("message", "暂无权限");
            return "/admin/error/404";
        }
        model.addAttribute("salesSlip", salesSlip);
        return "/admin/salesslip/print";
    }
    
    /**
     * @Description 导出保单 
     * @param request
     * @param response
     * @return    
     * @return Boolean     
     * @version V1.0
     * @auth    邹立强   (zoulq@cloud-young.com)
     * 2019年3月31日 下午10:10:55
     */
    @GetMapping("export")
    @ResponseBody
    public Boolean export(ServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = WebUtils.getParametersStartingWith(request, "s_");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (!map.isEmpty()) {
            String customeType = (String) map.get("custometype");
            if (StringUtils.isNotBlank(customeType)) {
                paramMap.put("customer_type", customeType);
            }
            String no = (String) map.get("no");
            if (StringUtils.isNotBlank(no)) {
                paramMap.put("no", no);
            }
            String customername = (String) map.get("customername");
            if (StringUtils.isNotBlank(customername)) {
                paramMap.put("customer_name", "%" + customername + "%");
            }
        }
        User currentUser = getCurrentUser();
        if (!currentUser.getIsSuper()) {
            paramMap.put("create_by", currentUser.getId());
        }
        List<SalesSlipVo> list = salesSlipService.getListSalesSlip(paramMap);
        List<SalesSlipExport> result=new ArrayList<SalesSlipExport>();
        Integer index=1;
        for (SalesSlipVo salesSlip : list) {
            SalesSlipExport salesSlipExport=new SalesSlipExport();
            salesSlipExport.setIndex(index++);
            salesSlipExport.setPolicyNo(salesSlip.getPolicyNo());
            salesSlipExport.setNo(salesSlip.getNo());
            salesSlipExport.setCustomerName(salesSlip.getCustomerName());
            if (1 == salesSlip.getCustomerType()) {
                salesSlipExport.setCustomerType("个人客户");
            } else {
                salesSlipExport.setCustomerType("企业客户");
            }
            salesSlipExport.setCertificateNo(salesSlip.getCertificateNo());
            salesSlipExport.setCustomerMobile(salesSlip.getCustomerMobile());
            salesSlipExport.setCustomerAddress(salesSlip.getCustomerAddress());
            salesSlipExport.setVehicleBrand(salesSlip.getVehicleBrand());
            salesSlipExport.setLicencePlateNo(salesSlip.getLicencePlateNo());
            salesSlipExport.setVehicleFrameNo(salesSlip.getVehicleFrameNo());
            salesSlipExport.setEngineFrameNo(salesSlip.getEngineFrameNo());
            salesSlipExport.setRegistrationDate(DateUtil.formatDateTime(salesSlip.getRegistrationDate(), DateUtil.LONG_DATE_FORMAT));
            salesSlipExport.setVehiclePrice(salesSlip.getVehiclePrice());
            salesSlipExport.setInstallDate(DateUtil.formatDateTime(salesSlip.getInstallDate(), DateUtil.LONG_DATE_FORMAT));
            salesSlipExport.setInstallAddress(salesSlip.getInstallAddress());
            salesSlipExport.setInstallser(salesSlip.getInstallser());
            salesSlipExport.setProductNo(salesSlip.getProductNo());
            salesSlipExport.setInsuranceTerm(salesSlip.getInsuranceTerm());
            salesSlipExport.setInsuranceStartDate(DateUtil.formatDateTime(salesSlip.getInsuranceStartDate(), DateUtil.LONG_DATE_FORMAT));
            salesSlipExport.setInsuranceEndDate(DateUtil.formatDateTime(salesSlip.getInsuranceEndDate(), DateUtil.LONG_DATE_FORMAT));
            salesSlipExport.setCompensatePrice(salesSlip.getCompensatePrice());
            salesSlipExport.setFirstBeneficiary(salesSlip.getFirstBeneficiary());
            salesSlipExport.setEntryTime(DateUtil.formatDateTime(salesSlip.getCreateDate(), DateUtil.FORMAT_ONE));
            salesSlipExport.setEntryName(salesSlip.getName());
            result.add(salesSlipExport);
        }
        try {
            String[] columnNames = { "序号", "保单号", " NO","客户名字", "客户类型","证件号","客户联系电话","通讯地址","车品牌","车牌号","车架号","发动机号","登记日期",
                    "初始车价格","安装日期","安装地点","安装人","产品SN码","保险期限","保险开始时间","保险结束时间","赔偿限额","第一受益人","录入时间","录入人"};
            String fileName = "保单表";
            ExportExcelWrapper<SalesSlipExport> util = new ExportExcelWrapper<SalesSlipExport>();
            util.exportExcel(fileName, fileName, columnNames, result, response, ExportExcelUtil.EXCEl_FILE_2007);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    
    /**
     * @Description 导出汇总单 
     * @param request
     * @param response
     * @return    
     * @return Boolean     
     * @version V1.0
     * @auth    邹立强   (zoulq@cloud-young.com)
     * 2019年3月31日 下午10:11:05
     */
    @GetMapping("exportSummary")
    @ResponseBody
    public Boolean exportSummary(ServletRequest request, HttpServletResponse response) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        User currentUser = getCurrentUser();
        if (!currentUser.getIsSuper()) {
            paramMap.put("create_by", currentUser.getId());
        }
        List<SummarySalesSlip> list = salesSlipService.getSummarySalesSlip(paramMap);
        Integer index=0;
        for (SummarySalesSlip summarySalesSlip : list) {
            index++;
            summarySalesSlip.setIndex(index);
        }
        try {
            String[] columnNames = { "序号", "授权网点", " 录入人", "录入总单数"};
            String fileName = "保单录入汇总表";
            ExportExcelWrapper<SummarySalesSlip> util = new ExportExcelWrapper<SummarySalesSlip>();
            util.exportExcel(fileName, fileName, columnNames, list, response, ExportExcelUtil.EXCEl_FILE_2007);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    
    /**
     * @Description redis分布式锁 
     * @param lockKey
     * @return boolean     
     * @version V1.0
     * @auth    邹立强   (zoulq@cloud-young.com)
     * 2019年3月31日 上午11:23:09
     */
    public synchronized boolean lock(String lockKey){
        boolean locked = false;
        Boolean success = redisTemplate.opsForValue().setIfAbsent(lockKey,"addSalesSliping");
        if(success != null && success){
            redisTemplate.expire(lockKey,10,TimeUnit.SECONDS);
            locked = true;
        }else{
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Boolean success2 = redisTemplate.opsForValue().setIfAbsent(lockKey,"addSalesSliping");
                if(success2 != null && success2){
                    redisTemplate.expire(lockKey,10,TimeUnit.SECONDS);
                    locked = true;
                    break;
                }
            }
        }
        return locked;
    }
    
    /**
     * @Description redis解锁 
     * @param lockKey    
     * @return void     
     * @version V1.0
     * @auth    邹立强   (zoulq@cloud-young.com)
     * 2019年3月31日 上午11:24:26
     */
    public  void unLock(String lockKey){
       redisTemplate.delete(lockKey);
    }
}
