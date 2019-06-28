package org.jmqtt.broker.modules.push.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jmqtt.broker.modules.push.dao.PushAppDao;
import org.jmqtt.broker.modules.push.entity.PushAppEntity;
import org.jmqtt.broker.modules.push.service.PushAppService;
import org.springframework.stereotype.Service;


@Service("pushAppService")
public class PushAppServiceImpl extends ServiceImpl<PushAppDao, PushAppEntity> implements PushAppService {

}