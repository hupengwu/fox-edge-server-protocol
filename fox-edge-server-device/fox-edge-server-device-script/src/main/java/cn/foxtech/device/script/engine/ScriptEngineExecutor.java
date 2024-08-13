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

package cn.foxtech.device.script.engine;

import cn.foxtech.common.entity.entity.BaseEntity;
import cn.foxtech.common.entity.entity.OperateEntity;
import cn.foxtech.device.protocol.v1.core.channel.FoxEdgeChannelService;
import cn.foxtech.device.protocol.v1.core.exception.ProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ScriptEngineExecutor {
    @Autowired
    private ExchangeService exchangeService;

    @Autowired
    private PublishService publishService;

    @Autowired
    private ReportService reportService;


    public Map<String, Object> exchange(String deviceName, String manufacturer, String deviceType, OperateEntity operateEntity, Map<String, Object> params, int timeout, FoxEdgeChannelService channelService) throws ProtocolException {
        return this.exchangeService.exchange(deviceName, manufacturer, deviceType, operateEntity, params, timeout, channelService);
    }

    public void publish(String deviceName, String manufacturer, String deviceType, OperateEntity operateEntity, Map<String, Object> params, int timeout, FoxEdgeChannelService channelService) throws ProtocolException {
        this.publishService.publish(deviceName, manufacturer, deviceType, operateEntity, params, timeout, channelService);
    }

    public Map<String, Object> decode(String manufacturer, String deviceType, List<BaseEntity> jspReportList, Object recv, Map<String, Object> params) throws ProtocolException {
        return this.reportService.decode(manufacturer, deviceType, jspReportList, recv, params);
    }
}