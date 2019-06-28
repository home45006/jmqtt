package org.jmqtt.broker;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jmqtt.broker.utils.RedisUtils;
import org.jmqtt.common.bean.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <功能说明>:
 *
 * @author yangyong
 * @date 2019-06-28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void contextLoads() {
        String redisKey = "jmqtt:test:key";
        Message message = new Message();
        message.setClientId("qqq@qq.com");
        redisUtils.set(redisKey, message);

        System.out.println(ToStringBuilder.reflectionToString(redisUtils.get(redisKey, Message.class)));
    }
}
