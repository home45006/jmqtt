package org.jmqtt.broker.rpc.dubbo;

import com.autopai.mq.push.api.pojo.BrokerNotificationBO;
import com.autopai.mq.push.api.pojo.enums.TargetTypeEnum;
import com.autopai.mq.push.api.service.BrokerNotficationService;
import org.apache.dubbo.config.annotation.Service;
import org.jmqtt.broker.dispatcher.MessageDispatcher;
import org.jmqtt.broker.utils.TopicPrefixEnum;
import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.MessageHeader;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangyong
 */
@Service(version = "1.0.0")
public class BrokerNotficationServiceImpl implements BrokerNotficationService {

    @Resource
    private MessageDispatcher messageDispatcher;

    @Override
    public Long notification(BrokerNotificationBO brokerNotificationBO) {

        if (TargetTypeEnum.CLIENT.value().equals( brokerNotificationBO.getTargetType() )) {
            brokerNotificationBO.getClientIds().forEach( clientId -> {
                Message innerMsg = new Message();
                innerMsg.setPayload( brokerNotificationBO.getMessage().getBytes() );
                innerMsg.setClientId( clientId );
                innerMsg.setType( Message.Type.PUBLISH );
                Map<String, Object> headers = new HashMap<>( 8 );
                headers.put( MessageHeader.TOPIC, TopicPrefixEnum.SUBSCRIBE_TOPIC.value() + brokerNotificationBO.getAppKey() + "/" + clientId );
                headers.put( MessageHeader.QOS, 0 );
                headers.put( MessageHeader.RETAIN, false );
                headers.put( MessageHeader.DUP, false );
                innerMsg.setHeaders( headers );
                innerMsg.setMsgId( -1 );
                this.messageDispatcher.appendMessage( innerMsg );
            } );
        } else {
            Message innerMsg = new Message();
            innerMsg.setPayload( brokerNotificationBO.getMessage().getBytes() );
            innerMsg.setType( Message.Type.PUBLISH );
            Map<String, Object> headers = new HashMap<>( 8 );
            headers.put( MessageHeader.TOPIC, TopicPrefixEnum.SUBSCRIBE_TOPIC.value() + brokerNotificationBO.getAppKey() );
            headers.put( MessageHeader.QOS, 0 );
            headers.put( MessageHeader.RETAIN, false );
            headers.put( MessageHeader.DUP, false );
            innerMsg.setHeaders( headers );
            innerMsg.setMsgId( -1 );
            this.messageDispatcher.appendMessage( innerMsg );
        }
        return null;
    }
}
