package com.mysiteforme.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mysiteforme.admin.dao.SalesSlipDao;
import com.mysiteforme.admin.entity.SalesSlip;
import com.mysiteforme.admin.entity.VO.SalesSlipVo;
import com.mysiteforme.admin.service.SalesSlipService;

/**
 * @Description 保单业务实现类
 * @date  2019年3月16日下午3:47:01
 * @version V1.0  
 * @author 邹立强   (zoulq@cloud-young.com)
 * <p>Copyright (c) Department of Research and Development/Beijing.</p>
 */
@Service("salesSlipService")
@Transactional(rollbackFor = Exception.class)
public class SalesSlipServiceImpl extends ServiceImpl<SalesSlipDao, SalesSlip> implements SalesSlipService{
    @Autowired
    private SalesSlipDao salesSlipDao;
    @Override
    public void saveSalesSlip(SalesSlip salesSlip) {
        baseMapper.insert(salesSlip);
    }

    @Override
    public List<SalesSlipVo> getListSalesSlip(Map<String, Object> paramMap) {
        return salesSlipDao.getListSalesSlip(paramMap);
    }

    @Override
    public Integer getTotal(Map<String, Object> paramMap) {
        return salesSlipDao.getTotal(paramMap);
    }
}
