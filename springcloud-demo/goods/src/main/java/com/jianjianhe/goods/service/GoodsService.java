package com.jianjianhe.goods.service;

import com.jianjianhe.goods.pojo.Goods;
import com.jianjianhe.entitybase.response.CommonQueryPageResponse;

import java.util.List;

public interface GoodsService {
    CommonQueryPageResponse<List<Goods>> goodsList(Integer pageNo, Integer pageSize);
}
