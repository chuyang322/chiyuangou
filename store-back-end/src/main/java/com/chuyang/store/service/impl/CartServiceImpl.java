package com.chuyang.store.service.impl;

import com.chuyang.store.mapper.CartMapper;
import com.chuyang.store.model.Cart;
import com.chuyang.store.service.CartService;
import com.chuyang.store.utils.APIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CartServiceImpl
 * @Description TODO:
 * @Author chuyang
 * @Date 2022/7/6 17:56
 * @Version 1.0
 **/
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Override
    public APIResult addCart(Cart cart) {
        int n=cartMapper.insert(cart);
        if(n!=0){
            return APIResult.createOKMessage("已添加到购物车");
        }else {
            return APIResult.createNg("添加购物车失败");
        }
    }

    @Override
    public APIResult deleteCart(Integer id) {
        int n=cartMapper.deleteById(id);
        if(n!=0){
            return APIResult.createOKMessage("删除成功");
        }else{
            return APIResult.createNg("删除失败");
        }
    }

    @Override
    public APIResult getCartInfoByUserId(Integer id,Integer current,Integer size) {
        List<Map<String,Object>> list=cartMapper.getCartInfoByUserId(id,current,size);
        return APIResult.createOk(list);
    }

    @Override
    public APIResult deleteCartByList(List<Integer> list) {
        int n=cartMapper.deleteBatchIds(list);
        if(n!=0){
            return APIResult.createOk("删除成功");
        }else {
            return APIResult.createNg("删除失败");
        }
    }
}
