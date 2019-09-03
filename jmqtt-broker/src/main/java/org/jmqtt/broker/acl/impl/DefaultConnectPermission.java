package org.jmqtt.broker.acl.impl;

import lombok.extern.slf4j.Slf4j;
import org.jmqtt.broker.acl.ConnectPermission;
import org.jmqtt.broker.security.AcceptAllAuthenticator;
import org.jmqtt.broker.security.IAuthenticator;
import org.jmqtt.broker.security.ResourceAuthenticator;
import org.jmqtt.common.config.BrokerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Service("DefaultConnectPermission")
public class DefaultConnectPermission implements ConnectPermission {

    private IAuthenticator authenticator;

    @Autowired
    public DefaultConnectPermission(BrokerConfig brokerConfig) {
        File defaultConfigurationFile = new File(brokerConfig.getPasswordFile());
        log.debug("Configuring MQTT authenticator");

        if (this.authenticator == null) {
            if (brokerConfig.getAllowAnonymous()) {
                this.authenticator = new AcceptAllAuthenticator();
            } else {
                this.authenticator = new ResourceAuthenticator(defaultConfigurationFile);
            }
            log.info("An {} authenticator instance will be used", authenticator.getClass().getName());
        }
    }

    @Override
    public boolean clientIdVerfy(String clientId) {
        return true;
    }

    @Override
    public boolean onBlacklist(String remoteAddr, String clientId) {
        return false;
    }

    @Override
    public boolean authentication(String clientId, String userName, byte[] password) {
        return authenticator.checkValid(clientId, userName, password);
    }

    @Override
    public boolean verfyHeartbeatTime(String clientId, int time) {
        return true;
    }
}
