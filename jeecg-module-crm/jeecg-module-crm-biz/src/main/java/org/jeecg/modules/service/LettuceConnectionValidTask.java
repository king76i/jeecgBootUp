package org.jeecg.modules.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description: $
 * @Author: 曾柏青
 * @date ：Created in 2022/4/2 14:48
 */
@Component
@Slf4j
public class LettuceConnectionValidTask {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Scheduled(cron="0/2 * * * * ?")
    public void task() {
        if (redisConnectionFactory instanceof LettuceConnectionFactory) {
            log.debug("redis连接监控");
            LettuceConnectionFactory c = (LettuceConnectionFactory) redisConnectionFactory;
            c.validateConnection();
        }
    }
}
