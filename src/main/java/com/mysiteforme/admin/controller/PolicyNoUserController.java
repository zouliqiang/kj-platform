package com.mysiteforme.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

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

import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.entity.PolicyNo;
import com.mysiteforme.admin.entity.PolicyNoUser;
import com.mysiteforme.admin.entity.User;
import com.mysiteforme.admin.entity.VO.PolicyNoUserVo;
import com.mysiteforme.admin.util.LayerData;
import com.mysiteforme.admin.util.RestResponse;

@Controller
@RequestMapping("/admin/policynouser")
public class PolicyNoUserController extends BaseController {

    @GetMapping("list")
    public String list() {
        return "/admin/policynouser/list";
    }

    @PostMapping("list")
    @ResponseBody
    public LayerData<PolicyNoUserVo> list(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "10") Integer limit, ServletRequest request) {
        Map<String, Object> map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<PolicyNoUserVo> layerData = new LayerData<>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("pageStart", (page - 1) * limit);
        paramMap.put("limit", limit);
        if (!map.isEmpty()) {
            String policyNo = (String) map.get("policyNo");
            if (StringUtils.isNotBlank(policyNo)) {
                paramMap.put("policy_no", policyNo);
            }
            String userName = (String) map.get("userName");
            if (StringUtils.isNotBlank(userName)) {
                paramMap.put("login_name", "%" + userName + "%");
            }
        }
        List<PolicyNoUserVo> list = policyNoUserService.getListPolicyNoUser(paramMap);
        Integer total = policyNoUserService.getTotal(paramMap);
        layerData.setData(list);
        layerData.setCount(total);
        return layerData;
    }

    @PostMapping("userlist")
    @ResponseBody
    public LayerData<User> userlist(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "10") Integer limit, ServletRequest request) {
        Map<String,Object> map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<User> userLayerData = new LayerData<>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("pageStart", (page - 1) * limit);
        paramMap.put("limit", limit);
        if (!map.isEmpty()) {
            String loginName = (String) map.get("loginName");
            if (StringUtils.isNotBlank(loginName)) {
                paramMap.put("login_name", "%" + loginName + "%");
            }
            String tel = (String) map.get("tel");
            if (StringUtils.isNotBlank(tel)) {
                paramMap.put("tel", tel);
            }
        }
        PolicyNo policyNo = policyNoService.getActivePolicyNo();
        paramMap.put("policyNo", policyNo.getPolicyNo());
        List<User> list = policyNoUserService.getListUserlist(paramMap);
        Integer total = policyNoUserService.getUserTotal(paramMap);
        userLayerData.setCount(total);
        userLayerData.setData(list);
        return userLayerData;
    }

    @GetMapping("add")
    public String add() {
        return "/admin/policynouser/add";
    }

    // @RequiresPermissions("salesslip:add")
    @PostMapping("add")
    @SysLog("分配保单")
    @ResponseBody
    public RestResponse add(@RequestBody PolicyNoUser policyNoUser) {
        if (policyNoUser==null) {
            return RestResponse.failure("参数异常");
        }
        Integer number1 = policyNoUser.getAllocationNumber();
        PolicyNo policyNo = policyNoService.selectById(policyNoUser.getId());
        Integer noTotal = policyNo.getNoTotal();
        Integer number2 = policyNo.getAllocationNumber();
        if (number1 + number2 > noTotal.intValue()) {
            return RestResponse.failure("分配数量超限");
        }
        User currentUser = getCurrentUser();
        policyNoUser.setId(null);
        policyNoUser.setPolicyNo(policyNo.getPolicyNo());
        policyNoUser.setUseNumber(0);
        policyNoUser.setCreateBy(currentUser.getId());
        policyNoUser.setCreateDate(new Date());
        policyNoUser.setDelFlag(0);
        policyNoUserService.insert(policyNoUser);
        return RestResponse.success();
    }

    @GetMapping("distribution")
    public String distribution(Long id, Model model) {
        if (id == null) {
            model.addAttribute("message", "暂无权限");
            return "/admin/error/404";
        }
        User user = userService.selectById(id);
        if (user == null) {
            model.addAttribute("message", "暂无用户信息");
            return "/admin/error/404";
        }
        PolicyNo policyNo = policyNoService.getActivePolicyNo();
        model.addAttribute("policyNo", policyNo);
        model.addAttribute("user", user);
        return "/admin/policynouser/distribution";
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
        PolicyNoUser PolicyNoUser = policyNoUserService.selectById(id);
        if (PolicyNoUser == null) {
            model.addAttribute("message", "暂无保单号信息");
            return "/admin/error/500";
        }
        model.addAttribute("PolicyNoUser", PolicyNoUser);
        return "/admin/policynous/look";
    }
}
