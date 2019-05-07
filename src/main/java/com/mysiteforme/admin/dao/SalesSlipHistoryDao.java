package com.mysiteforme.admin.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mysiteforme.admin.entity.SalesSlipHistory;
import com.mysiteforme.admin.entity.VO.SalesSlipVo;


public interface SalesSlipHistoryDao extends BaseMapper<SalesSlipHistory> {

    List<SalesSlipVo> getListSalesSlipHistory(Map<String, Object> paramMap);

    Integer getTotal(Map<String, Object> paramMap);

}
