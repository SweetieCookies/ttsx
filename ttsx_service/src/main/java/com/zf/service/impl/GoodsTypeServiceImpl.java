package com.zf.service.impl;

import com.zf.dao.GoodsTypeDao;
import com.zf.pojo.GoodsType;
import com.zf.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class GoodsTypeServiceImpl implements GoodsTypeService {
    @Autowired
    private GoodsTypeDao goodsTypeDao;
    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    @Override
    public List<GoodsType> queryAllGoodsType() {
        List<GoodsType> goodsTypes = goodsTypeDao.findAll();
        return goodsTypes;
    }

    @Override
    public GoodsType queryGoodsById(Integer id) {
        GoodsType goodsType = goodsTypeDao.findById(id);
        return goodsType;
    }
}
