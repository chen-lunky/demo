package com.jianjianhe.customer.controller;

import com.jianjianhe.customer.client.GoodsClinent;
import com.jianjianhe.entitybase.response.CommonQueryPageResponse;
import com.jianjianhe.goods.pojo.Goods;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Resource
    private GoodsClinent goodsClinent;
    @GetMapping("/list/{pageNo}/{pageSize}")
    public CommonQueryPageResponse<List<Goods>> goodsList(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize){
        CommonQueryPageResponse<List<Goods>> response = goodsClinent.goodsList(pageNo, pageSize);
        return response;
    }
}
