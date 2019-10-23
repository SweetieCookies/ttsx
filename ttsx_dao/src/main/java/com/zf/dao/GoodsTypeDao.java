package com.zf.dao;

import com.zf.pojo.GoodsType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsTypeDao {
    List<GoodsType> findAll();
    GoodsType findById(@Param("id") Integer id);
}
