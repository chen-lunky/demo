package com.jianjianhe.customer.client;




import com.jianjianhe.entitybase.response.CommonQueryPageResponse;
import com.jianjianhe.goods.pojo.Goods;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Component
@FeignClient(value = "jianjianhe-goods")
public interface GoodsClinent {
    @GetMapping("goods/list/{pageNo}/{pageSize}")
    CommonQueryPageResponse<List<Goods>> goodsList(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize);
}
