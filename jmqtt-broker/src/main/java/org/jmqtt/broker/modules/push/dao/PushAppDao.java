package org.jmqtt.broker.modules.push.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.jmqtt.broker.modules.push.entity.PushAppEntity;

/**
 * 应用信息
 *
 * @author yangyong
 * @email p_pyongyang@tencent.com
 * @date 2019-06-27 13:53:45
 */
@Mapper
public interface PushAppDao extends BaseMapper<PushAppEntity> {

}
