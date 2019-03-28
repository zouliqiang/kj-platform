package com.mysiteforme.admin.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.mysiteforme.admin.entity.SalesSlip;
import com.mysiteforme.admin.entity.VO.SalesSlipVo;
import com.mysiteforme.admin.entity.VO.SummarySalesSlip;

/**
 * @Description 保单服务类
 * @date  2019年3月26日下午11:45:27
 * @version V1.0  
 * @author 邹立强   (zoulq@cloud-young.com)
 * <p>Copyright (c) Department of Research and Development/Beijing.</p>
 */
public interface SalesSlipService extends IService<SalesSlip> {

    public void saveSalesSlip(SalesSlip salesSlip);

    public List<SalesSlipVo> getListSalesSlip(Map<String, Object> paramMap);

    public Integer getTotal(Map<String, Object> paramMap);

    public List<SummarySalesSlip> getSummarySalesSlip(Map<String, Object> paramMap);

}
