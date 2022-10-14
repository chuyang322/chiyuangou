package com.chuyang.store.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chuyang.store.model.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {
    @Select("select cart.id,goods.image,goods.name,goods.info,goods.price,cart.number from cart,goods where cart.goods=goods.id and cart.user=${id} limit ${(current-1)*size},${size}")
    public List<Map<String,Object>> getCartInfoByUserId(Integer id,Integer current,Integer size);
}
