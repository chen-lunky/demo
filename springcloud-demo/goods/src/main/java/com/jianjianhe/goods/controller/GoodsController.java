package com.jianjianhe.goods.controller;



import com.jianjianhe.goods.pojo.Goods;
import com.jianjianhe.goods.service.GoodsService;
import com.jianjianhe.entitybase.response.CommonQueryPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @GetMapping("/list/{pageNo}/{pageSize}")
    public CommonQueryPageResponse<List<Goods>> goodsList(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize){
        return goodsService.goodsList(pageNo,pageSize);
    }
}
