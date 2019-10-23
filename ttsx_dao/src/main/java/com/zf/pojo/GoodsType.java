package com.zf.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class GoodsType {
    private Integer id;
    private String title;
    private String className;
    private String bannerPath;
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;
    public GoodsType(Integer id, String title, String className, String bannerPath, Date createTime) {
        this.id = id;
        this.title = title;
        this.className = className;
        this.bannerPath = bannerPath;
        this.createTime = createTime;
    }
    private List<Goods> goods;

}
