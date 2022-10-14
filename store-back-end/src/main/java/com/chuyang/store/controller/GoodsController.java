package com.chuyang.store.controller;

import com.chuyang.store.model.Goods;
import com.chuyang.store.service.GoodsService;
import com.chuyang.store.utils.APIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName GoodsController
 * @Description TODO:
 * @Author chuyang
 * @Date 2022/7/6 11:37
 * @Version 1.0
 **/
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/goods",method = RequestMethod.POST)
    public APIResult createGoods(@RequestBody Goods goods){
        return goodsService.createGoods(goods);
    }

    @RequestMapping(value = "/list/{current}/{size}",method = RequestMethod.GET)
    public APIResult getList(@PathVariable("current")Integer current,@PathVariable("size")Integer size){
        return goodsService.getList(current,size);
    }

    @RequestMapping(value = "/goods/{id}",method = RequestMethod.DELETE)
    public APIResult deleteGoodsById(@PathVariable("id") Integer id){
        return goodsService.deleteGoodsById(id);
    }

    @RequestMapping(value = "/klist/{current}/{size}/{keyword}")
    public APIResult getListByKeyword(@PathVariable("current")Integer current,@PathVariable("size")Integer size,@PathVariable("keyword")String keyword){
        return goodsService.getListByKeyword(current,size,keyword);
    }
}
