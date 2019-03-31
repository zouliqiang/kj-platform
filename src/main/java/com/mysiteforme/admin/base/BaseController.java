package com.mysiteforme.admin.base;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.mysiteforme.admin.entity.User;
import com.mysiteforme.admin.realm.AuthRealm.ShiroUser;
import com.mysiteforme.admin.service.LogService;
import com.mysiteforme.admin.service.MenuService;
import com.mysiteforme.admin.service.PolicyNoService;
import com.mysiteforme.admin.service.PolicyNoUserService;
import com.mysiteforme.admin.service.RescourceService;
import com.mysiteforme.admin.service.RoleService;
import com.mysiteforme.admin.service.SalesSlipService;
import com.mysiteforme.admin.service.SiteService;
import com.mysiteforme.admin.service.UploadInfoService;
import com.mysiteforme.admin.service.UserService;

public class BaseController {
	
	public User getCurrentUser() {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if(shiroUser == null) {
			return null;
		}
		User loginUser = userService.selectById(shiroUser.getId());
		return loginUser;
	}

	@Autowired
	protected UserService userService;

	@Autowired
	protected MenuService menuService;

	@Autowired
	protected RoleService roleService;

	@Autowired
	protected RescourceService rescourceService;

	@Autowired
	protected SiteService siteService;

	@Autowired
	protected LogService logService;

	@Autowired
	protected UploadInfoService uploadInfoService;
	
    	@Autowired
        protected SalesSlipService salesSlipService;
    	
    	@Autowired
    	protected PolicyNoService policyNoService;
    	
    	@Autowired
        protected PolicyNoUserService policyNoUserService;
}
