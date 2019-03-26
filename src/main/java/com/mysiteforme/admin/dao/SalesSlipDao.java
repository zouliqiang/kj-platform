package com.mysiteforme.admin.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mysiteforme.admin.entity.SalesSlip;
import com.mysiteforme.admin.entity.VO.SalesSlipVo;


public interface SalesSlipDao extends BaseMapper<SalesSlip> {

    List<SalesSlipVo> getListSalesSlip(Map<String, Object> paramMap);

    Integer getTotal(Map<String, Object> paramMap);

}
