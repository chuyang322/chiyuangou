package com.chuyang.store.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Goods
 * @Description TODO:
 * @Author chuyang
 * @Date 2022/7/6 11:23
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer price;
    private String image;
    private String Info;
}
