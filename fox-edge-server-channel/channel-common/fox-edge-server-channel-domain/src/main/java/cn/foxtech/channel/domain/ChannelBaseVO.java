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

package cn.foxtech.channel.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class ChannelBaseVO {
    /**
     * 交换模式：发送并等待返回，比如对设备的问答查询
     */
    public static String MODE_EXCHANGE = "exchange";
    /**
     * 发布模式：只发送不等待接收，比如对设备的单向广播
     */
    public static String MODE_PUBLISH = "publish";
    /**
     * 接收模式：只接收不发送，比如设备的主动通知上报
     */
    public static String MODE_RECEIVE = "receive";

    /**
     * 通道类型
     */
    private String type;
    /**
     * UUID
     */
    private String uuid;
    /**
     * 通道名称
     */
    private String name;
    /**
     * 发送模式：问答模式，只送模式，接收模式
     */
    private String mode = ChannelBaseVO.MODE_EXCHANGE;
    /**
     * 发送数据
     */
    private Object send;
    /**
     * 接收到的数据
     */
    private Object recv;
    /**
     * 通信超时
     */
    private Integer timeout;

    /**
     * 绑定信息：方便将request的信息复制给respond
     *
     * @param vo
     */
    public void bindBaseVO(ChannelBaseVO vo) {
        this.type = vo.type;
        this.uuid = vo.uuid;
        this.name = vo.name;
        this.mode = vo.mode;
        this.send = vo.send;
        this.recv = vo.recv;
        this.timeout = vo.timeout;
    }

    public void bindBaseVO(Map<String, Object> map) {
        this.type = (String) map.get("type");
        this.uuid = (String) map.get("uuid");
        this.name = (String) map.get("name");
        this.mode = (String) map.get("mode");
        this.send = map.get("send");
        this.recv = map.get("recv");
        this.timeout = (Integer) map.get("timeout");
    }
}
