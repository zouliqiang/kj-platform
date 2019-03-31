package com.mysiteforme.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mysiteforme.admin.dao.PolicyNoUserDao;
import com.mysiteforme.admin.entity.PolicyNoUser;
import com.mysiteforme.admin.entity.User;
import com.mysiteforme.admin.entity.VO.PolicyNoUserVo;
import com.mysiteforme.admin.service.PolicyNoUserService;

/**
 * @Description 保单号分配实现类
 * @date  2019年3月16日下午3:47:01
 * @version V1.0  
 * @author 邹立强   (zoulq@cloud-young.com)
 * <p>Copyright (c) Department of Research and Development/Beijing.</p>
 */
@Service("policyNoUserService")
@Transactional(rollbackFor = Exception.class)
public class PolicyNoUserServiceImpl extends ServiceImpl<PolicyNoUserDao, PolicyNoUser> implements PolicyNoUserService{
    
    @Autowired
    private PolicyNoUserDao policyNoUserDao;

    @Override
    public List<PolicyNoUserVo> getListPolicyNoUser(Map<String, Object> paramMap) {
        return policyNoUserDao.getListPolicyNoUser(paramMap);
    }

    @Override
    public Integer getTotal(Map<String, Object> paramMap) {
        return policyNoUserDao.getTotal(paramMap);
    }

    @Override
    public List<User> getListUserlist(Map<String, Object> paramMap) {
        return policyNoUserDao.getListUserlist(paramMap);
    }

    @Override
    public Integer getUserTotal(Map<String, Object> paramMap) {
        return policyNoUserDao.getUserTotal(paramMap);
    }

    @Override
    public PolicyNoUser getPolicyNoUser(Map<String, Object> map) {
        return policyNoUserDao.getPolicyNoUser(map);
    }
   
}
