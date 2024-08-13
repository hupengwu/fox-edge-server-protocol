/* ----------------------------------------------------------------------------
 * Copyright (c) Guangzhou Fox-Tech Co., Ltd. 2020-2024. All rights reserved.
 *
 *     This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 * --------------------------------------------------------------------------- */

package cn.foxtech.common.entity.service.redis;

import cn.foxtech.common.entity.entity.BaseEntity;
import com.fasterxml.jackson.core.JsonParseException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Redis数据的消费者：
 * 1、消费者会使用isNeedLoad去redis服务器上去查询，上游的生产者是否发布了数据更新到redis中
 * 2、消费者发现有数据更新，那么就从redis服务器loadEntities数据到自己的内存中
 */
public abstract class BaseConsumerRedisService extends BaseRedisService {
    /**
     * 根据redis的时间戳，判定数据是否已经被生产者更新
     *
     * @return
     */
    @Override
    public boolean isNeedLoad() {
        return super.isNeedLoad();
    }

    /**
     * 从redis重新读取数据到本地内存中
     */
    @Override
    public void loadAllEntities() throws JsonParseException {
        super.loadAllEntities();
    }

    /**
     * 绑定通知
     *
     * @param notify
     */
    @Override
    public void bind(BaseConsumerTypeNotify notify) {
        super.bind(notify);
    }

    @Override
    public List<BaseConsumerEntityNotify> getEntityNotify() {
        return super.getEntityNotify();
    }

    public void bindEntityNotify(BaseConsumerEntityNotify entityNotify) {
        this.getEntityNotify().add(entityNotify);
    }

    /**
     * 从redis重新读取数据到本地内存中
     */
    @Override
    public void loadAgileEntities() throws IOException {
        super.loadAgileEntities();
    }


    /**
     * 获得一个副本
     *
     * @return
     */
    @Override
    public Map<String, BaseEntity> getEntitys() {
        return super.getEntitys();
    }

    /**
     * 获取实体
     *
     * @return 实体
     */
    @Override
    public BaseEntity getEntity(String entityKey) {
        return super.getEntity(entityKey);
    }

    @Override
    public Map<String, BaseEntity> getEntityMap(Collection<String> entityKeys) {
        return super.getEntityMap(entityKeys);
    }

    /**
     * 根据Key特征，查询实体
     *
     * @param entity 实体
     */
    @Override
    public BaseEntity selectEntity(BaseEntity entity) {
        return super.selectEntity(entity);
    }
}
