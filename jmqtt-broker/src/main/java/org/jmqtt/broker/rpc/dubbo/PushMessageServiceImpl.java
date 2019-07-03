package org.jmqtt.broker.rpc.dubbo;

import com.autopai.mq.push.api.pojo.RpcResult;
import com.autopai.mq.push.api.service.PushMessageService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @ClassName PushMessageServiceImpl
 * @Description: TODO
 * @Author: david
 * @create: 2019-07-03 11:37
 **/
@Service(version = "${demo.service.version}")
public class PushMessageServiceImpl implements PushMessageService {
    @Override
    public Boolean pushMessage(String s) {
        return true;
    }

    @Override
    public RpcResult pushMessage(String client, String message) {
        return null;
    }
}
