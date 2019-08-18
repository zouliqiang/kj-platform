package com.mysiteforme.admin.controller.system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.google.common.collect.Lists;
import com.mysiteforme.admin.annotation.SysLog;
import com.mysiteforme.admin.base.BaseController;
import com.mysiteforme.admin.base.MySysUser;
import com.mysiteforme.admin.entity.Role;
import com.mysiteforme.admin.entity.User;
import com.mysiteforme.admin.entity.VO.ShowMenu;
import com.mysiteforme.admin.util.Constants;
import com.mysiteforme.admin.util.LayerData;
import com.mysiteforme.admin.util.RestResponse;
import com.mysiteforme.admin.util.ToolUtil;

/**
 * Created by wangl on 2017/11/21.
 *
 */
@Controller
@RequestMapping("admin/system/user")
public class UserConteroller extends BaseController{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserConteroller.class);

    @GetMapping("list")
    @SysLog("跳转系统用户列表页面")
    public String list(){
        return "admin/system/user/list";
    }

    @RequiresPermissions("sys:user:list")
    @PostMapping("list")
    @ResponseBody
    public LayerData<User> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                HttpServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<User> userLayerData = new LayerData<>();
        EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
            String userId = request.getParameter("userId");
            String loginname = (String) map.get("loginname");
            if(StringUtils.isNotBlank(loginname)) {
                userEntityWrapper.like("login_name", loginname);
            }
            String tel = (String) map.get("tel");

            if(StringUtils.isNotBlank(tel)) {
                userEntityWrapper.eq("tel",tel);
            }
            if(StringUtils.isNotBlank(userId)) {
                userEntityWrapper.ne("id",Long.valueOf(userId));
                userEntityWrapper.eq("is_super",0);
            }
            String nickname = (String) map.get("nickname");
            if(StringUtils.isNotBlank(nickname)) {
                userEntityWrapper.like("nick_name", nickname);
            }
            String email = (String) map.get("email");
            if(StringUtils.isNotBlank(email)) {
                userEntityWrapper.like("email", email);
            }
            String website = (String) map.get("website");
            if(StringUtils.isNotBlank(website)) {
                userEntityWrapper.like("website", website);
            }
        Page<User> userPage = userService.selectPage(new Page<>(page,limit),userEntityWrapper);
        List<User> records = userPage.getRecords();
        for (User user : records) {
            Boolean isSuper = user.getIsSuper();
            if(isSuper) {
                user.setLevel("一级");
            }else {
                String childIds = user.getChildIds();
                if(StringUtils.isNotBlank(childIds)) {
                    user.setLevel("二级");
                }else {
                    user.setLevel("三级");
                }
            }
        }
        userLayerData.setCount(userPage.getTotal());
        userLayerData.setData(userPage.getRecords());
        return  userLayerData;
    }

    @GetMapping("add")
    public String add(Model model){
        List<Role> roleList = roleService.selectAll();
        model.addAttribute("roleList",roleList);
        return "admin/system/user/add";
    }

    @RequiresPermissions("sys:user:add")
    @PostMapping("add")
    @ResponseBody
    @SysLog("保存新增系统用户数据")
    public RestResponse add(@RequestBody  User user){
        if(StringUtils.isBlank(user.getLoginName())){
            return RestResponse.failure("登录名不能为空");
        }
        if(user.getRoleLists() == null || user.getRoleLists().size() == 0){
            return  RestResponse.failure("用户角色至少选择一个");
        }
        if(userService.userCount(user.getLoginName())>0){
            return RestResponse.failure("登录名称已经存在");
        }
        if(StringUtils.isNoneBlank(user.getTel())){
            if(userService.userCount(user.getTel())>0){
                return RestResponse.failure("该手机号已被绑定");
            }
        }
        user.setPassword(Constants.DEFAULT_PASSWORD);
        userService.saveUser(user);
        if(user.getId() == null || user.getId() == 0){
            return RestResponse.failure("保存用户信息出错");
        }
        return RestResponse.success();
    }
    @RequiresPermissions("sys:user:addChildAccount")
    @PostMapping("addChildAccount")
    @ResponseBody
    @SysLog("设置子账户")
    public RestResponse addChildAccount(HttpServletRequest request){
        String userId = request.getParameter("userId");
        String id = request.getParameter("id");
        if(StringUtils.isBlank(userId)){
            return RestResponse.failure("绑定用户Id不能为空");
        }
        if(StringUtils.isBlank(id)){
            return  RestResponse.failure("绑定子账户不能为空");
        }
        User user = userService.findUserById(Long.valueOf(userId));
        String childIds = user.getChildIds();
        if(StringUtils.isNotBlank(childIds)) {
            String[] split = childIds.split(",");
            List<String> asList = Arrays.asList(split);
            List<String> list =new ArrayList<String>(asList);
            list.add(id);
            Set<String> staffsSet = new HashSet<>(list);
            StringBuffer sb =new StringBuffer();
            for (String idSet:staffsSet) {
                sb.append(idSet).append(",");
            }
            childIds = sb.substring(0, sb.length()-1);
            user.setChildIds(childIds);
        }else {
            user.setChildIds(id);
        }
        userService.updateUser(user);
        return RestResponse.success();
    }
    
    @RequiresPermissions("sys:user:cancelAddChildAccount")
    @PostMapping("cancelAddChildAccount")
    @ResponseBody
    @SysLog("取消设置子账户")
    public RestResponse cancelAddChildAccount(HttpServletRequest request){
        String userId = request.getParameter("userId");
        String id = request.getParameter("id");
        if(StringUtils.isBlank(userId)){
            return RestResponse.failure("绑定用户Id不能为空");
        }
        if(StringUtils.isBlank(id)){
            return  RestResponse.failure("绑定子账户不能为空");
        }
        User user = userService.findUserById(Long.valueOf(userId));
        String childIds = user.getChildIds();
        if(StringUtils.isNotBlank(childIds)) {
            String[] split = childIds.split(",");
            List<String> asList = Arrays.asList(split);
            Set<String> staffsSet = new HashSet<>(asList);
            staffsSet.remove(id);
            StringBuffer sb =new StringBuffer();
            if(staffsSet.size()>0) {
            for (String idSet:staffsSet) {
                sb.append(idSet).append(",");
            }
            childIds = sb.substring(0, sb.length()-1);
            user.setChildIds(childIds);
            }else {
                user.setChildIds("");
            }
        }
        userService.updateUser(user);
        return RestResponse.success();
    }

    
    @GetMapping("edit")
    public String edit(Long id,Model model){
        User user = userService.selectById(id);
        List<Long> roleIdList = Lists.newArrayList();
        if(user != null) {
            Set<Role> roleSet = user.getRoleLists();
            if (roleSet != null && roleSet.size() > 0) {
                for (Role r : roleSet) {
                    roleIdList.add(r.getId());
                }
            }
        }
        List<Role> roleList = roleService.selectAll();
        model.addAttribute("localuser",user);
        model.addAttribute("roleIds",roleIdList);
        model.addAttribute("roleList",roleList);
        return "admin/system/user/edit";
    }

    @RequiresPermissions("sys:user:edit")
    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存系统用户编辑数据")
    public RestResponse edit(@RequestBody  User user){
        if(user.getId() == 0 || user.getId() == null){
            return RestResponse.failure("用户ID不能为空");
        }
        if(StringUtils.isBlank(user.getLoginName())){
            return RestResponse.failure("登录名不能为空");
        }
        if(user.getRoleLists() == null || user.getRoleLists().size() == 0){
            return  RestResponse.failure("用户角色至少选择一个");
        }
        User oldUser = userService.findUserById(user.getId());
        if(StringUtils.isNotBlank(user.getLoginName())){
            if(!user.getLoginName().equals(oldUser.getLoginName())) {
                if (userService.userCount(user.getLoginName()) > 0) {
                    return RestResponse.failure("该登录名已存在");
                }
            }
        }
        if(StringUtils.isNotBlank(user.getTel())){
            if(!user.getTel().equals(oldUser.getTel())) {
                if (userService.userCount(user.getTel()) > 0) {
                    return RestResponse.failure("该手机号已经被绑定");
                }
            }
        }
        if(user.getId()==1) {
            user.setIsSuper(true); 
        }
        user.setIcon(oldUser.getIcon());
        userService.updateUser(user);
        return RestResponse.success();
    }

    @RequiresPermissions("sys:user:delete")
    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除系统用户数据(单个)")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(id == null || id == 0 || id == 1){
            return RestResponse.failure("参数错误");
        }
        User user = userService.findUserById(id);
        if(user == null){
            return RestResponse.failure("用户不存在");
        }
        userService.deleteUser(user);
        return RestResponse.success();
    }

    @RequiresPermissions("sys:user:delete")
    @PostMapping("deleteSome")
    @ResponseBody
    @SysLog("删除系统用户数据(多个)")
    public RestResponse deleteSome(@RequestBody List<User> users){
        if(users == null || users.size()==0){
            return RestResponse.failure("请选择需要删除的用户");
        }
        for (User u : users){
            if(u.getId() == 1){
                return RestResponse.failure("不能删除超级管理员");
            }else{
                userService.deleteUser(u);
            }
        }
        return RestResponse.success();
    }

    /***
     * 获得用户所拥有的菜单列表
     * @return
     */
    @GetMapping("getUserMenu")
    @ResponseBody
    public List<ShowMenu> getUserMenu(){
        Long userId = MySysUser.id();
        List<ShowMenu> list = menuService.getShowMenuByUser(userId);
        return list;
    }

    @GetMapping("userinfo")
    public String toEditMyInfo(Model model){
        Long userId = MySysUser.id();
        User user = userService.findUserById(userId);
        user.setIcon(Constants.WEB_IP+user.getIcon());
        model.addAttribute("userinfo",user);
        model.addAttribute("userRole",user.getRoleLists());
        return "admin/system/user/userInfo";
    }

    @PostMapping("saveUserinfo")
    @SysLog("系统用户个人信息修改")
    @ResponseBody
    public RestResponse saveUserInfo(User user){
        if(user.getId() == 0 || user.getId() == null){
            return RestResponse.failure("用户ID不能为空");
        }
        if(StringUtils.isBlank(user.getLoginName())){
            return RestResponse.failure("登录名不能为空");
        }
        User oldUser = userService.findUserById(user.getId());
        if(StringUtils.isNotBlank(user.getTel())){
            if(!user.getTel().equals(oldUser.getTel())) {
                if (userService.userCount(user.getTel()) > 0) {
                    return RestResponse.failure("该手机号已经被绑定");
                }
            }
        }
        if(user.getId()==1) {
            user.setIsSuper(true); 
        }
        if(StringUtils.isNotBlank(user.getIcon())) {
            int one = user.getIcon().lastIndexOf("/");
            user.setIcon(user.getIcon().substring((one+1),user.getIcon().length()));
        }
        user.setRoleLists(oldUser.getRoleLists());
        userService.updateUser(user);
        return RestResponse.success();
    }

    @GetMapping("changePassword")
    public String changePassword(){
        return "admin/system/user/changePassword";
    }
    
    @GetMapping("setChildList")
    public String setChildList(HttpServletRequest request){
        String userId = request.getParameter("id");
        User user = userService.findUserById(Long.valueOf(userId));
        request.setAttribute("user", user);
        return "admin/system/user/childIds";
    }
  

    @RequiresPermissions("sys:user:changePassword")
    @PostMapping("changePassword")
    @SysLog("用户修改密码")
    @ResponseBody
    public RestResponse changePassword(@RequestParam(value = "oldPwd",required = false)String oldPwd,
                                       @RequestParam(value = "newPwd",required = false)String newPwd,
                                       @RequestParam(value = "confirmPwd",required = false)String confirmPwd){
        if(StringUtils.isBlank(oldPwd)){
            return RestResponse.failure("旧密码不能为空");
        }
        if(StringUtils.isBlank(newPwd)){
            return RestResponse.failure("新密码不能为空");
        }
        if(StringUtils.isBlank(confirmPwd)){
            return RestResponse.failure("确认密码不能为空");
        }
        if(!confirmPwd.equals(newPwd)){
            return RestResponse.failure("确认密码与新密码不一致");
        }
        User c=getCurrentUser();
        User user = userService.findUserById(MySysUser.id());
        user.setIsSuper(c.getIsSuper());
        //旧密码不能为空
        String pw = ToolUtil.entryptPassword(oldPwd,user.getSalt()).split(",")[0];
        if(!user.getPassword().equals(pw)){
            return RestResponse.failure("旧密码错误");
        }
        user.setPassword(newPwd);
        ToolUtil.entryptPassword(user);
        userService.updateUser(user);
        return RestResponse.success();
    }

}
