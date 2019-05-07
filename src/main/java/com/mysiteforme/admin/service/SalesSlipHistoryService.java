package com.mysiteforme.admin.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.mysiteforme.admin.entity.SalesSlipHistory;
import com.mysiteforme.admin.entity.VO.SalesSlipVo;


public interface SalesSlipHistoryService extends IService<SalesSlipHistory> {

    List<SalesSlipVo> getListSalesSlipHistory(Map<String, Object> paramMap);

    Integer getTotal(Map<String, Object> paramMap);

}
