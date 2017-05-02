package com.mjoys.advert.deploy.controller;

import com.mjoys.common.wolf.model.ReturnValue;
import com.mjoys.common.wolf.redis.ShardJedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 创 建 人 : leiliang.<br/>
 * 创建时间 : 2017/5/2 11:58.<br/>
 * 功能描述 : .<br/>
 * 变更记录 : .<br/>
 */
@Controller
@RequestMapping("test")
public class TestController {

    /**
     * The Balance redis client.
     */
    @Autowired
    private ShardJedisClient balanceRedisClient;

    @Autowired
    private ShardJedisClient goodsRedisClient;

    /**
     * 广告主资质审核接口
     *
     * @return return value
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "redisGet")
    public ReturnValue<String> redisGet() {

        ReturnValue<String> result = balanceRedisClient.get("test");
        if (result.isSuccessful()) {
            return ReturnValue.successResult(result.getValue());
        }
        return ReturnValue.failResult(result.getMsg());
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "redisSet")
    public ReturnValue<String> redisSet() {

        goodsRedisClient.setex("test1", "123", 3600);
        return goodsRedisClient.get("test1");
    }
}
