package com.zf.service;

import com.zf.pojo.GoodsType;

import java.util.List;

public interface GoodsTypeService {
    List<GoodsType> queryAllGoodsType();
    GoodsType queryGoodsById(Integer id);
}
