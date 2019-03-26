package com.mysiteforme.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mysiteforme.admin.dao.PolicyNoDao;
import com.mysiteforme.admin.entity.PolicyNo;
import com.mysiteforme.admin.service.PolicyNoService;

/**
 * @Description 保单号业务实现类
 * @date  2019年3月16日下午3:47:01
 * @version V1.0  
 * @author 邹立强   (zoulq@cloud-young.com)
 * <p>Copyright (c) Department of Research and Development/Beijing.</p>
 */
@Service("policyNoService")
@Transactional(rollbackFor = Exception.class)
public class PolicyNoServiceImpl extends ServiceImpl<PolicyNoDao, PolicyNo> implements PolicyNoService{
    
    @Autowired
    private PolicyNoDao policyNoDao;

    @Override
    public Integer updateEnableState(int enableState) {
        return policyNoDao.updateEnableState(enableState);
    }

    @Override
    public PolicyNo getActivePolicyNo() {
        return policyNoDao.getActivePolicyNo();
    }
   
}
