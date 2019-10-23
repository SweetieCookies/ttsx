package com.zf.controller;

import com.github.pagehelper.PageInfo;
import com.zf.pojo.GoodsType;
import com.zf.service.GoodsTypeService;
import com.zf.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("goodsType")
public class GoodsTypeController {
    @Autowired
    private GoodsTypeService goodsTypeService;
    @GetMapping("queryAll")
    public String queryAll(HttpServletRequest request){
        List<GoodsType> goodsTypes = goodsTypeService.queryAllGoodsType();
        request.setAttribute("goodsTypes", goodsTypes);
        for (GoodsType goodsType : goodsTypes) {
            System.out.println(goodsType);
        }
        return "/WEB-INF/goodsType";
    }
    @GetMapping("query")
    public String query(HttpServletRequest request,String id){
        if(StringUtils.isEmpty(id)){
            System.out.println("id未传入");
        }
        GoodsType goodsType = goodsTypeService.queryGoodsById(Integer.parseInt(id));
        request.setAttribute("goodsType", goodsType);

    }
}
