package com.chuyang.store.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chuyang.store.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
