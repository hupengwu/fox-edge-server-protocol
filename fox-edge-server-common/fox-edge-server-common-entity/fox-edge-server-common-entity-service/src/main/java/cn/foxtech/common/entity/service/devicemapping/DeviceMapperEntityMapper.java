package cn.foxtech.common.entity.service.devicemapping;


import cn.foxtech.common.entity.entity.DeviceMapperEntity;
import cn.foxtech.common.entity.entity.DeviceMapperPo;
import cn.foxtech.common.entity.service.mybatis.BaseEntityMapper;
import org.springframework.stereotype.Repository;

//在对应的Mapper 接口上 基础基本的 BaseMapper<T> T是对应的pojo类
@Repository   //告诉容器你是持久层的 @Repository是spring提供的注释，能够将该类注册成Bean
public interface DeviceMapperEntityMapper extends BaseEntityMapper<DeviceMapperPo> {
}

