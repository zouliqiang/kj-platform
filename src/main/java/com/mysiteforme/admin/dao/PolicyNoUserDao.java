package com.mysiteforme.admin.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mysiteforme.admin.entity.PolicyNoUser;
import com.mysiteforme.admin.entity.User;
import com.mysiteforme.admin.entity.VO.PolicyNoUserVo;


public interface PolicyNoUserDao extends BaseMapper<PolicyNoUser> {

    List<PolicyNoUserVo> getListPolicyNoUser(Map<String, Object> paramMap);

    Integer getTotal(Map<String, Object> paramMap);

    List<User> getListUserlist(Map<String, Object> paramMap);

    Integer getUserTotal(Map<String, Object> paramMap);

}
