package com.mysiteforme.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mysiteforme.admin.dao.SalesSlipHistoryDao;
import com.mysiteforme.admin.entity.SalesSlipHistory;
import com.mysiteforme.admin.entity.VO.SalesSlipVo;
import com.mysiteforme.admin.service.SalesSlipHistoryService;

/**
 * @Description 保单业务实现类
 * @date  2019年3月16日下午3:47:01
 * @version V1.0  
 * @author 邹立强   (zoulq@cloud-young.com)
 * <p>Copyright (c) Department of Research and Development/Beijing.</p>
 */
@Service("salesSlipHistoryService")
@Transactional(rollbackFor = Exception.class)
public class SalesSlipHistoryServiceImpl extends ServiceImpl<SalesSlipHistoryDao, SalesSlipHistory> implements SalesSlipHistoryService{
    @Autowired
    private SalesSlipHistoryDao salesSlipHistoryDao;

    @Override
    public List<SalesSlipVo> getListSalesSlipHistory(Map<String, Object> paramMap) {
        return salesSlipHistoryDao.getListSalesSlipHistory(paramMap);
    }

    @Override
    public Integer getTotal(Map<String, Object> paramMap) {
        return salesSlipHistoryDao.getTotal(paramMap);
    }
}
