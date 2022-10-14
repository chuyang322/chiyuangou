package com.chuyang.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignCode {
    private String code;
    // 时间戳
    private Long createTime;
    // 有效时间（毫秒）
    private Long lifeTime;

}
