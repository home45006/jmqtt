package org.jmqtt.broker.acl.impl;

import org.jmqtt.broker.acl.PubSubPermission;
import org.springframework.stereotype.Service;

@Service("DefaultPubSubPermission")
public class DefaultPubSubPermission implements PubSubPermission {

    @Override
    public boolean publishVerfy(String clientId, String topic) {
        return true;
    }

    @Override
    public boolean subscribeVerfy(String clientId, String topic) {
        return true;
    }
}
