package com.chuyang.store.service;

import com.chuyang.store.model.Goods;
import com.chuyang.store.utils.APIResult;

public interface GoodsService {
    public APIResult createGoods(Goods goods);
    public APIResult getList(int current,int size);
    public APIResult deleteGoodsById(Integer id);
    public APIResult getListByKeyword(Integer current,Integer size,String keyword);
}
