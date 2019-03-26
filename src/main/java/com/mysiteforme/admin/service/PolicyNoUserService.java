package com.mysiteforme.admin.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.mysiteforme.admin.entity.PolicyNoUser;
import com.mysiteforme.admin.entity.User;
import com.mysiteforme.admin.entity.VO.PolicyNoUserVo;

/**
 * @Description policy_no_user
 * @date  2019年3月22日上午12:01:11
 * @version V1.0  
 * @author 邹立强   (zoulq@cloud-young.com)
 * <p>Copyright (c) Department of Research and Development/Beijing.</p>
 */
public interface PolicyNoUserService extends IService<PolicyNoUser> {

    List<PolicyNoUserVo> getListPolicyNoUser(Map<String, Object> paramMap);

    Integer getTotal(Map<String, Object> paramMap);

    List<User> getListUserlist(Map<String, Object> paramMap);

    Integer getUserTotal(Map<String, Object> paramMap);



}
