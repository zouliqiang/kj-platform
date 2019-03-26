package com.mysiteforme.admin.dao;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mysiteforme.admin.entity.PolicyNo;


public interface PolicyNoDao extends BaseMapper<PolicyNo> {

    Integer updateEnableState(@Param("enableState")int enableState);

    PolicyNo getActivePolicyNo();
}
