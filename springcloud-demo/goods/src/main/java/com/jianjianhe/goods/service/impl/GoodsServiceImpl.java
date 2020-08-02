package com.jianjianhe.goods.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jianjianhe.entitybase.constant.ResponseCodeConstant;
import com.jianjianhe.entitybase.response.CommonQueryPageResponse;
import com.jianjianhe.goods.mapper.GoodsMapper;
import com.jianjianhe.goods.pojo.Goods;
import com.jianjianhe.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public CommonQueryPageResponse<List<Goods>> goodsList(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Goods> goodsList = goodsMapper.selectAll();
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
        CommonQueryPageResponse<List<Goods>> response = new CommonQueryPageResponse<>();
        response.setResponse(pageInfo.getList());
        response.setPageSize(pageInfo.getPageSize());
        response.setTotalCount(pageInfo.getTotal());
        response.setTotalPage(pageInfo.getPages());
        response.setCurrent(pageInfo.getPageNum());
        response.setCode(ResponseCodeConstant.SUCCESS.getResponseCode());
        response.setContent(ResponseCodeConstant.SUCCESS.getResponseDesc());
        response.setSuccess(true);
        response.setTimestamp(System.currentTimeMillis());
        return response;
    }
}
