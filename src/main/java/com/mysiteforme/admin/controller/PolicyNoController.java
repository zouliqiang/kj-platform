package com.mysiteforme.admin.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.baomidou.mybatisplus.plugins.Page;
import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.entity.PolicyNo;
import com.mysiteforme.admin.entity.User;
import com.mysiteforme.admin.util.LayerData;
import com.mysiteforme.admin.util.RestResponse;

/**
 * @Description 保单号管理
 * @date  2019年3月31日下午10:14:41
 * @version V1.0  
 * @author 邹立强   (zoulq@cloud-young.com)
 * <p>Copyright (c) Department of Research and Development/Beijing.</p>
 */
@Controller
@RequestMapping("/admin/policyno")
public class PolicyNoController extends BaseController {
    
    /**
     * @Description 进入保单设置列表 
     * @return    
     * @return String     
     * @version V1.0
     * @auth    邹立强   (zoulq@cloud-young.com)
     * 2019年3月31日 下午10:14:56
     */
    @GetMapping("list")
    public String list() {
        return "/admin/policyno/list";
    }
    
    /**
     * @Description 保单列表接口 
     * @param page
     * @param limit
     * @param request
     * @return LayerData<PolicyNo>     
     * @version V1.0
     * @auth    邹立强   (zoulq@cloud-young.com)
     * 2019年3月31日 下午10:15:17
     */
    @PostMapping("list")
    @ResponseBody
    public LayerData<PolicyNo> list(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "10") Integer limit, ServletRequest request) {
        Map<String,Object> map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<PolicyNo> layerData = new LayerData<>();
        EntityWrapper<PolicyNo> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag",false);
        if(!map.isEmpty()){
            String policyno = (String) map.get("policyno");
            if(StringUtils.isNotBlank(policyno)) {
                wrapper.eq("policy_no",policyno);
            }else{
                map.remove("policy_no");
            }
        }
        Page<PolicyNo> pageData = policyNoService.selectPage(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount(pageData.getTotal());
        return layerData;
    }
    
    /**
     * @Description 增加保单号 
     * @return String     
     * @version V1.0
     * @auth    邹立强   (zoulq@cloud-young.com)
     * 2019年3月31日 下午10:15:35
     */
    @GetMapping("add")
    public String add() {
        return "/admin/policyno/add";
    }
    
    /**
     * @Description 保存保单号数据 
     * @param policyNo
     * @return RestResponse     
     * @version V1.0
     * @auth    邹立强   (zoulq@cloud-young.com)
     * 2019年3月31日 下午10:15:48
     */
    
    @RequiresPermissions("policyno:add")
    @PostMapping("add")
    @SysLog("添加保单号数据")
    @ResponseBody
    public RestResponse add(@RequestBody PolicyNo policyNo) {
        PolicyNo isPolicyNo=policyNoService.getPolicyNoByPolicyNo(policyNo.getPolicyNo());
        if(isPolicyNo!=null) {
           return RestResponse.failure("保单号已存在，不可重复添加");
        }
        Date date = new Date();
        User currentUser = getCurrentUser();
        Integer enableState = policyNo.getEnableState();
        if(enableState==1) {
            policyNoService.updateEnableState(0);
        }
        policyNo.setDelFlag(0);
        policyNo.setNewNo(policyNo.getStartNo());
        policyNo.setNoNumber(0);
        policyNo.setAllocationNumber(0);
        policyNo.setCreateId(currentUser.getCreateId());
        policyNo.setCreateDate(date);
        policyNoService.insert(policyNo);
        return RestResponse.success();
    }
    
    /**
     * @Description 删除保单号信息 
     * @param id
     * @return RestResponse     
     * @version V1.0
     * @auth    邹立强   (zoulq@cloud-young.com)
     * 2019年3月31日 下午10:16:02
     */
    @RequiresPermissions("policyno:delete")
    @PostMapping("delete")
    @SysLog("删除保单号数据")
    @ResponseBody
    public RestResponse delete(Long id) {
        Date date = new Date();
        User currentUser = getCurrentUser();
        PolicyNo policyNo=new PolicyNo();
        policyNo.setId(id);
        policyNo.setDelFlag(1);
        policyNo.setUpdateDate(date);
        policyNo.setUpdateId(currentUser.getId());
        policyNo.setDelFlag(1);
        policyNoService.updateById(policyNo);
        return RestResponse.success();
    }
    
    /**
     * @Description 编辑保单号信息 
     * @param policyNo
     * @return RestResponse     
     * @version V1.0
     * @auth    邹立强   (zoulq@cloud-young.com)
     * 2019年3月31日 下午10:16:26
     */
    @RequiresPermissions("policyno:edit")
    @PostMapping("edit")
    @SysLog("编辑保单号数据")
    @ResponseBody
    public RestResponse edit(@RequestBody PolicyNo policyNo) {
        Date date = new Date();
        User currentUser = getCurrentUser();
        policyNo.setUpdateDate(date);
        policyNo.setUpdateId(currentUser.getId());
        Integer enableState = policyNo.getEnableState();
        if(enableState==1) {
            policyNoService.updateEnableState(0);;
        }
        policyNoService.updateById(policyNo);
        return RestResponse.success();
    }
    
    /**
     * @Description 进入保单号编辑界面 
     * @param id
     * @param model
     * @return String     
     * @version V1.0
     * @auth    邹立强   (zoulq@cloud-young.com)
     * 2019年3月31日 下午10:16:59
     */
    @GetMapping("edit")
    public String edit(Long id, Model model) {
        if (id == null) {
            model.addAttribute("message", "暂无权限");
            return "/admin/error/500";
        }
        PolicyNo policyNo = policyNoService.selectById(id);
        if (policyNo == null) {
            model.addAttribute("message", "暂无保单号信息");
            return "/admin/error/500";
        }
        model.addAttribute("policyNo", policyNo);
        return "/admin/policyno/edit";
    }
}
