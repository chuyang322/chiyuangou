package com.chuyang.store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuyang.store.mapper.GoodsMapper;
import com.chuyang.store.model.Goods;
import com.chuyang.store.model.User;
import com.chuyang.store.service.GoodsService;
import com.chuyang.store.utils.APIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName GoodsServiceImpl
 * @Description TODO:
 * @Author chuyang
 * @Date 2022/7/6 11:36
 * @Version 1.0
 **/
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public APIResult createGoods(Goods goods) {
        int n=goodsMapper.insert(goods);
        if(n!=0){
            return APIResult.createOKMessage("添加成功");
        }else {
            return APIResult.createNg("添加失败");
        }
    }

    @Override
    public APIResult getList(int current, int size) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<Goods>();
        Page<Goods> page = new Page(current, size);
        IPage<Goods> ipage = goodsMapper.selectPage(page, wrapper);
        APIResult apiResult = new APIResult();
        apiResult.setCode(200);
        apiResult.setMessage("查询成功");
        apiResult.setData(ipage);
        return apiResult;
    }

    @Override
    public APIResult deleteGoodsById(Integer id) {
        int n = goodsMapper.deleteById(id);
        if(n!=0){
            return APIResult.createOKMessage("删除成功");
        }else {
            return APIResult.createNg("删除失败");
        }
    }

    @Override
    public APIResult getListByKeyword(Integer current, Integer size, String keyword) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<Goods>();
        wrapper.like("name",keyword);
        wrapper.like("info",keyword);
        Page<Goods> page = new Page(current, size);
        IPage<Goods> ipage = goodsMapper.selectPage(page, wrapper);
        APIResult apiResult = new APIResult();
        apiResult.setCode(200);
        apiResult.setMessage("查询成功");
        apiResult.setData(ipage);
        return apiResult;
    }
}
