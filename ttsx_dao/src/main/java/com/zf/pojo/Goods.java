package com.zf.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    private Integer id;
    private String title;
    private String imgPath;
    private String bannerPath;
    private BigDecimal price;
    private String detail;
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;
    private Integer typeId;
}
