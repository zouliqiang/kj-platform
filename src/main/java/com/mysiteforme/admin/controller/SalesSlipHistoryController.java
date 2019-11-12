package com.mysiteforme.admin.controller;


import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.entity.User;
import com.mysiteforme.admin.entity.VO.SalesSlipVo;
import com.mysiteforme.admin.util.LayerData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

/**
 * @Description 保单控制
 * @date  2019年3月31日下午10:08:53
 * @version V1.0  
 * @author 邹立强   (zoulq@cloud-young.com)
 * <p>Copyright (c) Department of Research and Development/Beijing.</p>
 */
@Controller
@RequestMapping("/admin/salessliphistory")
public class SalesSlipHistoryController extends BaseController{
    
    
    /**
     * @Description 进入保单编辑历史列表页 
     * @return String     
     * @version V1.0
     * @auth    邹立强   (zoulq@cloud-young.com)
     * 2019年3月31日 下午10:09:07
     */
    @GetMapping("list")
    public String list() {
        return "/admin/salesslip/historylist";
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
    @PostMapping("historylist")
    @ResponseBody
    public LayerData<SalesSlipVo> list(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "10") Integer limit, ServletRequest request) {
        Map<String,Object> map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<SalesSlipVo> layerData = new LayerData<>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("pageStart", (page - 1) * limit);
        paramMap.put("limit", limit);
        if (!map.isEmpty()) {
            String customername = (String) map.get("customername");
            if (StringUtils.isNotBlank(customername)) {
                paramMap.put("customer_name", "%" + customername + "%");
            }
            String certificateNo = (String) map.get("certificateNo");
            if (StringUtils.isNotBlank(certificateNo)) {
                paramMap.put("certificate_no",certificateNo);
            }
            String tel = (String) map.get("tel");
            if (StringUtils.isNotBlank(tel)) {
                paramMap.put("tel", tel );
            }
            String website = (String) map.get("website");
            if (StringUtils.isNotBlank(website)) {
                paramMap.put("website", "%" + certificateNo + "%");
            }
            String insuranceRange = (String) map.get("insurance_range");
            if (StringUtils.isNotBlank(insuranceRange)) {
                String[] split = insuranceRange.split(" - ");
                paramMap.put("insurance_start_date", split[0]+" 00:00:00");
                paramMap.put("insurance_end_date", split[1]+" 23:59:59");
            }
        }
        User currentUser = getCurrentUser();
        if (!currentUser.getIsSuper()) {
            paramMap.put("create_by", currentUser.getId());
        }
        List<SalesSlipVo> list = salesSlipHistoryService.getListSalesSlipHistory(paramMap);
        Integer total = salesSlipHistoryService.getTotal(paramMap);
        layerData.setData(list);
        layerData.setCount(total);
        return layerData;
    }
}
    
