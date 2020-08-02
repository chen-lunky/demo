package com.jianjianhe.redisseckill.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
@RequestMapping("/goods")
public class SeckillController {
    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedissonClient redissonClient;
    /**
     * 购买成功数量
     */
    private AtomicInteger sellCount = new AtomicInteger(0);

    /**
     * 初始化商品库存数量
     * @return
     */
    @GetMapping("/initcount")
    public String initcount() {
        stringRedisTemplate.opsForValue().set("goods_count", "200");
        sellCount.set(0);
        System.out.println("初始化库存成功");
        return "初始化库存成功";
    }

    /**
     * 通过加锁方式减少库存方式
     * @return
     */
    @GetMapping("/secKill")
    public void secKill() {
        RLock rLock = redissonClient.getLock("goods_count");
        Integer goods_count =0;
        try {
            rLock.lock();
            goods_count = Integer.parseInt(stringRedisTemplate.opsForValue().get("goods_count"));
            goods_count = goods_count - 1;
            if (goods_count < 0) {
                return;
            }
            stringRedisTemplate.opsForValue().set("goods_count", goods_count.toString());
//            System.out.println("减少库存成功,共减少" + sellCount.incrementAndGet());
        } finally {
            rLock.unlock();
            if (goods_count <0){
                System.out.println("对不起，该商品卖完了");
            }else {
                System.out.println("恭喜抢购成功!!");
            }
        }
    }
    @GetMapping("/sell")
    public void sell() {

    }



}
