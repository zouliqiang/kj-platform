package com.mysiteforme.admin.base;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.mysiteforme.admin.entity.User;
import com.mysiteforme.admin.realm.AuthRealm.ShiroUser;
import com.mysiteforme.admin.service.BlogArticleService;
import com.mysiteforme.admin.service.BlogChannelService;
import com.mysiteforme.admin.service.BlogCommentService;
import com.mysiteforme.admin.service.BlogTagsService;
import com.mysiteforme.admin.service.DictService;
import com.mysiteforme.admin.service.LogService;
import com.mysiteforme.admin.service.MenuService;
import com.mysiteforme.admin.service.PolicyNoService;
import com.mysiteforme.admin.service.PolicyNoUserService;
import com.mysiteforme.admin.service.RescourceService;
import com.mysiteforme.admin.service.RoleService;
import com.mysiteforme.admin.service.SalesSlipService;
import com.mysiteforme.admin.service.SiteService;
import com.mysiteforme.admin.service.TableService;
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
	protected DictService dictService;

	@Autowired
	protected RescourceService rescourceService;

	@Autowired
	protected TableService tableService;

	@Autowired
	protected SiteService siteService;

	@Autowired
	protected LogService logService;

	@Autowired
	protected BlogArticleService blogArticleService;

	@Autowired
	protected BlogChannelService blogChannelService;

	@Autowired
	protected BlogCommentService blogCommentService;

	@Autowired
	protected BlogTagsService blogTagsService;

	@Autowired
	protected UploadInfoService uploadInfoService;
	
    	@Autowired
        protected SalesSlipService salesSlipService;
    	
    	@Autowired
    	protected PolicyNoService policyNoService;
    	
    	@Autowired
        protected PolicyNoUserService policyNoUserService;
}
