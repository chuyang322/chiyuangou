package com.chuyang.store.controller;

import com.chuyang.store.model.Cart;
import com.chuyang.store.service.CartService;
import com.chuyang.store.utils.APIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CartController
 * @Description TODO:
 * @Author chuyang
 * @Date 2022/7/6 18:02
 * @Version 1.0
 **/
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/carts",method = RequestMethod.POST)
    public APIResult addCart(@RequestBody Cart cart){
        return cartService.addCart(cart);
    }

    @RequestMapping(value = "/carts/{id}",method = RequestMethod.DELETE)
    public APIResult removeCart(@PathVariable("id") Integer id){
        return cartService.deleteCart(id);
    }

    @RequestMapping(value = "/carts/{id}/{current}/{size}",method = RequestMethod.GET)
    public APIResult getCartByUserId(@PathVariable("id")Integer id,@PathVariable("current")Integer current,@PathVariable("size")Integer size){
        return cartService.getCartInfoByUserId(id,current,size);
    }

    @RequestMapping(value = "/deleteByIds",method = RequestMethod.GET)
    public APIResult deleteCartByList(@RequestParam("list") int[] idList){
        List<Integer> list=new ArrayList<>();
        for (int i:idList) {
            list.add(i);
        }
        return cartService.deleteCartByList(list);
    }
}
