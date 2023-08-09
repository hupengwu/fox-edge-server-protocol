package cn.foxtech.common.entity.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@TableName("tb_device_mapper")
public class DeviceMapperEntity extends DeviceObjInfEntity {
    /**
     * 映射名称
     */
    private String mapperName;
    /**
     * 映射模式：
     * replace    0  替换，例如将"对象1"重命名成“object 1”，只保留
     * duplicate  1  副本，例如为“对象1”新增一个"object 1"的副本，同时保留"对象1"
     * filter     2  过滤，例如将“对象1”剔除掉
     */
    private Integer mapperMode;

    /**
     * 获得init方法
     *
     * @return 初始化方法
     * @throws NoSuchMethodException 方法异常
     */
    public static Method getInitMethod() throws NoSuchMethodException {
        return DeviceMapperEntity.class.getMethod("init", DeviceObjInfEntity.class);
    }

    /**
     * 业务Key
     *
     * @return 业务Key
     */
    public List<Object> makeServiceKeyList() {
        return super.makeServiceKeyList();
    }

    /**
     * 查询过滤器
     *
     * @return 过滤器
     */
    public Object makeWrapperKey() {
        return super.makeWrapperKey();
    }

    /**
     * 获取业务值
     *
     * @return 数值成员列表
     */
    public List<Object> makeServiceValueList() {
        List<Object> list = new ArrayList<>();
        list.add(this.mapperName);
        list.add(this.mapperMode);
        return list;
    }

    public void bind(DeviceMapperEntity other) {
        super.bind(other);

        this.mapperName = other.mapperName;
        this.mapperMode = other.mapperMode;
    }

    public void init(DeviceObjInfEntity other) {
        this.bind(other);

        this.mapperName = other.getObjectName();
        this.mapperMode = 0;
    }
}
