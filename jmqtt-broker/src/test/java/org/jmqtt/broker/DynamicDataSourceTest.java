package org.jmqtt.broker;

import org.jmqtt.broker.modules.push.entity.PushAppEntity;
import org.jmqtt.broker.modules.push.service.PushAppService;
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
public class DynamicDataSourceTest {

    @Autowired
    private PushAppService pushAppService;

    @Test
    public void saveTest() {
        PushAppEntity pushAppEntity = new PushAppEntity();
        pushAppEntity.setAppKey("11");
        pushAppEntity.setUserId(123123L);
        pushAppService.save(pushAppEntity);
    }
}
