package com.chuyang.store.service;

import com.chuyang.store.model.Cart;
import com.chuyang.store.utils.APIResult;

import java.util.List;
import java.util.Map;

public interface CartService {
    public APIResult addCart(Cart cart);
    public APIResult deleteCart(Integer id);
    public APIResult getCartInfoByUserId(Integer id,Integer current,Integer size);
    public APIResult deleteCartByList(List<Integer> list);
}
