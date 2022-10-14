package com.chuyang.store.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String password;
    private Long createTime;
    private String avatar;
    private Boolean sex;
    private String email;
    private String phone;
}
