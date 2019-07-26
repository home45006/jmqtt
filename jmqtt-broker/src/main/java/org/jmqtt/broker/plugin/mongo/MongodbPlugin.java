package org.jmqtt.broker.plugin.mongo;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jmqtt.broker.plugin.PluginHook;
import org.jmqtt.broker.utils.TopicPrefixEnum;
import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.MessageHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author yangyong
 */
@Component
public class MongodbPlugin implements PluginHook {

    private static final String TAGS = "tags";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void receiveMessage(Message message) {
        String topic = String.valueOf( message.getHeader( MessageHeader.TOPIC ) );
        if (StringUtils.isNotEmpty( topic ) && StringUtils.contains( topic, TopicPrefixEnum.PUBLIC_TOPIC.value() )) {
            String[] topicPaths = StringUtils.split( topic , "/" );
            String clientId = topicPaths[2];
            String appKey = topicPaths[1];
            JSONObject jsonObject = JSONObject.parseObject( new String( message.getPayload(), IOUtils.UTF8 ) );
            if (jsonObject != null && jsonObject.containsKey( TAGS )) {
                ClientTag clientTag = new ClientTag();
                JSONArray jsonArray = jsonObject.getJSONArray( TAGS );
                clientTag.setClientId( clientId );
                clientTag.setTags( jsonArray.toJavaList( String.class ) );
                clientTag.setAppKey( appKey );
                clientTag.setCreateTime( new Date() );
                mongoTemplate.save( clientTag );
            }
        }
    }
}
