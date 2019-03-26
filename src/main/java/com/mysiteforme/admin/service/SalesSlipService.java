package com.mysiteforme.admin.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.mysiteforme.admin.entity.SalesSlip;
import com.mysiteforme.admin.entity.VO.SalesSlipVo;

/**
 * <p>
 * 系统日志 服务类
 * </p>
 *
 * @author wangl
 * @since 2018-01-13
 */
public interface SalesSlipService extends IService<SalesSlip> {

    public void saveSalesSlip(SalesSlip salesSlip);

    public List<SalesSlipVo> getListSalesSlip(Map<String, Object> paramMap);

    public Integer getTotal(Map<String, Object> paramMap);

}
