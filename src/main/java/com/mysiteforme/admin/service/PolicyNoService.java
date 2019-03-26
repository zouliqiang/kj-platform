package com.mysiteforme.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.mysiteforme.admin.entity.PolicyNo;

/**
 * @Description policy_no
 * @date  2019年3月22日上午12:01:11
 * @version V1.0  
 * @author 邹立强   (zoulq@cloud-young.com)
 * <p>Copyright (c) Department of Research and Development/Beijing.</p>
 */
public interface PolicyNoService extends IService<PolicyNo> {

    Integer updateEnableState(int enableState);

    PolicyNo getActivePolicyNo();



}
