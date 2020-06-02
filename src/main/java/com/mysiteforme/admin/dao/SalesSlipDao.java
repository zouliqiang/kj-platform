package com.mysiteforme.admin.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mysiteforme.admin.entity.SalesSlip;
import com.mysiteforme.admin.entity.VO.SalesSlipVo;
import com.mysiteforme.admin.entity.VO.SummarySalesSlip;


public interface SalesSlipDao extends BaseMapper<SalesSlip> {

    List<SalesSlipVo> getListSalesSlip(Map<String, Object> paramMap);

    Integer getTotal(Map<String, Object> paramMap);

    List<SummarySalesSlip> getSummarySalesSlip(Map<String, Object> paramMap);

    List<Long> getIdsByUserIds(List<Long> lists);

}
