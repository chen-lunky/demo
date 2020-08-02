package com.jianjianhe.redisseckill.queue;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 通过redis队列的原子操作实现秒杀
 */
public class RedisSpike {
    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxWaitMillis(10000);
        jedisPoolConfig.setMaxTotal(1024);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);


        Jedis jedis = new Jedis();
        //把商品的数量入队，有十件商品
        jedis.lpush("goodslist","1","2","3","4","5","6","7","8","9","10");
        ExecutorService executor = Executors.newFixedThreadPool(30);
        //模拟30个人抢
        for (int i = 1; i <= 30; i++) {
            executor.execute(new SpikeTask(i, jedisPool));
        }

        executor.shutdown();

    }
}

class SpikeTask implements Runnable {

    private int customerId;

    private JedisPool jedisPool;

    public SpikeTask (int customerId, JedisPool jedisPool) {
        this.customerId = customerId;
        this.jedisPool = jedisPool;
    }

    @Override
    public void run() {

        // 执行秒杀
        Jedis client = jedisPool.getResource();

        //弹出
        String goodsId = client.lpop("goodslist");

        if (goodsId != null && goodsId.length() != 0) {
            System.out.println("顾客" + customerId + "抢到了第" + goodsId + "件商品");
        } else {
            System.out.println("顾客" + customerId + "没有抢到商品");
        }

    }
}
