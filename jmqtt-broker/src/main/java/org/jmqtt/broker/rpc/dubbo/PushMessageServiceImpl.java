package org.jmqtt.broker.rpc.dubbo;

import com.autopai.mq.push.api.pojo.RpcResult;
import com.autopai.mq.push.api.pojo.enums.RpcReturnCode;
import com.autopai.mq.push.api.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.jmqtt.broker.dispatcher.MessageDispatcher;
import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.MessageHeader;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName PushMessageServiceImpl
 * @Description: TODO
 * @Author: david
 * @create: 2019-07-03 11:37
 **/
@Slf4j
@Service(version = "1.0.0")
public class PushMessageServiceImpl implements PushMessageService {

    @Resource
    private MessageDispatcher messageDispatcher;

    @Override
    public Boolean pushMessage(String s) {
        log.info("hello: {}", s);
        return true;
    }

    @Override
    public RpcResult pushMessage(String clientId, String message) {
        RpcResult rpcResult = new RpcResult();
        Message innerMsg = new Message();
        innerMsg.setPayload(message.getBytes());
        innerMsg.setClientId(clientId);
        innerMsg.setType(Message.Type.PUBLISH);
        Map<String,Object> headers = new HashMap<>();
        headers.put(MessageHeader.TOPIC, "test");
        headers.put(MessageHeader.QOS, 0);
        headers.put(MessageHeader.RETAIN, false);
        headers.put(MessageHeader.DUP, false);
        innerMsg.setHeaders(headers);
        innerMsg.setMsgId(-1);

        this.messageDispatcher.appendMessage(innerMsg);

        rpcResult.setRpcReturnCode(RpcReturnCode.SUCCESS);
        rpcResult.setReturnInfo("推送成功");
        return rpcResult;
    }
}
