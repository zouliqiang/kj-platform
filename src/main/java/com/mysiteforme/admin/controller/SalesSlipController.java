package com.mysiteforme.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.entity.SalesSlip;
import com.mysiteforme.admin.entity.User;
import com.mysiteforme.admin.entity.VO.SalesSlipVo;
import com.mysiteforme.admin.util.DateUtil;
import com.mysiteforme.admin.util.LayerData;
import com.mysiteforme.admin.util.RestResponse;
import com.mysiteforme.admin.util.poi.ExcelFileBuilder;
import com.mysiteforme.admin.util.poi.ResponseUtil;
import com.mysiteforme.admin.util.poi.data.CellData;
import com.mysiteforme.admin.util.poi.data.TextCellData;

@Controller
@RequestMapping("/admin/salesslip")
public class SalesSlipController extends BaseController {

    @GetMapping("list")
    public String list() {
        return "/admin/salesslip/list";
    }

    @PostMapping("list")
    @ResponseBody
    public LayerData<SalesSlipVo> list(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "10") Integer limit, ServletRequest request) {
        Map map = WebUtils.getParametersStartingWith(request, "s_");
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

    @GetMapping("add")
    public String add() {
        return "/admin/salesslip/add";
    }

    // @RequiresPermissions("salesslip:add")
    @PostMapping("add")
    @SysLog("保存保单数据")
    @ResponseBody
    public RestResponse add(@RequestBody SalesSlip salesSlip) {
        Date date = new Date();
        User currentUser = getCurrentUser();
        salesSlip.setPolicyNo("12345678");
        salesSlip.setNo("2");
        salesSlip.setCreateBy(currentUser.getId());
        salesSlip.setCreateDate(date);
        salesSlip.setDelFlag(0);
        salesSlip.setInsuranceEndDate(DateUtil.getNextYear(salesSlip.getInsuranceStartDate(), salesSlip.getInsuranceTerm()));
        if ("1".equals(salesSlip.getCustomerType())) {
            salesSlip.setCertificateType(1);
        } else {
            salesSlip.setCertificateType(2);
        }
        salesSlipService.saveSalesSlip(salesSlip);
        return RestResponse.success();
    }

    @GetMapping("look")
    public String edit(Long id, Model model) {
        User currentUser = getCurrentUser();
        if (id == null) {
            model.addAttribute("message", "暂无权限");
            return "/admin/error/500";
        }
        if (id != null && !currentUser.getIsSuper() && currentUser.getId().intValue() != id.intValue()) {
            model.addAttribute("message", "暂无权限");
            return "/admin/error/500";
        }
        SalesSlip salesSlip = salesSlipService.selectById(id);
        if (salesSlip == null) {
            model.addAttribute("message", "暂无保单信息");
            return "/admin/error/500";
        }
        model.addAttribute("salesSlip", salesSlip);
        return "/admin/salesslip/look";
    }

    @GetMapping("print")
    public String print(Long id, Model model) {
        User currentUser = getCurrentUser();
        if (id == null) {
            model.addAttribute("message", "暂无权限");
            return "/admin/error/404";
        }
        if (id != null && !currentUser.getIsSuper() && currentUser.getId().intValue() != id.intValue()) {
            model.addAttribute("message", "暂无权限");
            return "/admin/error/404";
        }
        SalesSlip salesSlip = salesSlipService.selectById(id);
        if (salesSlip == null) {
            model.addAttribute("message", "暂无保单信息");
            return "/admin/error/404";
        }
        model.addAttribute("salesSlip", salesSlip);
        return "/admin/salesslip/print";
    }

    @GetMapping("export")
    @ResponseBody
    public Boolean export(ServletRequest request, HttpServletResponse response) {
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Wrapper<SalesSlip> wrapper = new EntityWrapper<>();
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
        List<List<CellData>> result = new ArrayList<List<CellData>>();
        for (SalesSlipVo salesSlip : list) {
            ArrayList<CellData> cellData = new ArrayList<CellData>();
            cellData.add(new TextCellData(salesSlip.getPolicyNo()));
            cellData.add(new TextCellData(salesSlip.getNo()));
            if (1 == salesSlip.getCustomerType()) {
                cellData.add(new TextCellData("个人客户"));
            } else {
                cellData.add(new TextCellData("企业客户"));
            }
            cellData.add(new TextCellData(salesSlip.getCertificateNo()));
            cellData.add(new TextCellData(salesSlip.getCustomerMobile()));
            cellData.add(new TextCellData(salesSlip.getCustomerAddress()));
            cellData.add(new TextCellData(salesSlip.getVehicleBrand()));
            cellData.add(new TextCellData(salesSlip.getLicencePlateNo()));
            cellData.add(new TextCellData(salesSlip.getVehicleFrameNo()));
            cellData.add(new TextCellData(salesSlip.getEngineFrameNo()));
            cellData.add(new TextCellData(salesSlip.getInstallAddress()));
            cellData.add(new TextCellData(salesSlip.getVehiclePrice().toString()));
            cellData.add(new TextCellData(DateUtil.formatDateTime(salesSlip.getInstallDate(), DateUtil.LONG_DATE_FORMAT)));
            cellData.add(new TextCellData(salesSlip.getInstallser()));
            cellData.add(new TextCellData(salesSlip.getProductNo()));
            cellData.add(new TextCellData(salesSlip.getInsuranceTerm().toString()));
            cellData.add(new TextCellData(DateUtil.formatDateTime(salesSlip.getInsuranceStartDate(), DateUtil.LONG_DATE_FORMAT)));
            cellData.add(new TextCellData(DateUtil.formatDateTime(salesSlip.getInsuranceEndDate(), DateUtil.LONG_DATE_FORMAT)));
            cellData.add(new TextCellData(salesSlip.getCompensatePrice().toString()));
            cellData.add(new TextCellData(salesSlip.getFirstBeneficiary()));
            cellData.add(new TextCellData(DateUtil.formatDateTime(salesSlip.getCreateDate(), DateUtil.FORMAT_ONE)));
            cellData.add(new TextCellData(salesSlip.getName()));
            result.add(cellData);
        }
        try {
            ResponseUtil.setResponseExcelFile(response, "保单");
            ExcelFileBuilder.init().addHeader(getReportHeader()).addContent(result).writeTo(response.getOutputStream()).finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * @Description 组装excel表头 
     * @return    
     * @return List<CellData>     
     * @version V1.0
     * @auth    邹立强   (zoulq@cloud-young.com)
     * 2018年4月20日 上午10:24:10
     */
    private List<CellData> getReportHeader() {
        List<CellData> result = new ArrayList<>();
        result.add(new TextCellData("保单号"));
        result.add(new TextCellData("NO"));
        result.add(new TextCellData("客户类型"));
        result.add(new TextCellData("证件号"));
        result.add(new TextCellData("客户联系电话"));
        result.add(new TextCellData("通讯地址"));
        result.add(new TextCellData("车品牌"));
        result.add(new TextCellData("车牌号"));
        result.add(new TextCellData("车架号"));
        result.add(new TextCellData("发动机号"));
        result.add(new TextCellData("初始车价格"));
        result.add(new TextCellData("安装日期"));
        result.add(new TextCellData("安装地点"));
        result.add(new TextCellData("安装人"));
        result.add(new TextCellData("产品SN码"));
        result.add(new TextCellData("保险期限"));
        result.add(new TextCellData("保险开始时间"));
        result.add(new TextCellData("保险结束时间"));
        result.add(new TextCellData("赔偿限额"));
        result.add(new TextCellData("第一受益人"));
        result.add(new TextCellData("录入时间"));
        result.add(new TextCellData("录入人"));
        return result;
    }
}
