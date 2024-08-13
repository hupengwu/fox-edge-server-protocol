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

package cn.foxtech.device.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 单步操作VO
 */
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class OperateVO {
    /**
     * UUID
     */
    private String uuid;
    /**
     * 操作模式
     */
    private String operateMode = "";
    /**
     * 设备名称
     */
    private String deviceName = "";

    /**
     * 设备类型名
     */
    private String deviceType = "";
    /**
     * 设备厂商
     */
    private String manufacturer = "";
    /**
     * 操作名称
     */
    private String operateName = "";
    /**
     * 操作参数
     */
    private Map<String, Object> param = new HashMap<>();
    /**
     * 通信超时
     */
    private Integer timeout;
    /**
     * 是否需要记录：默认不需要
     */
    private Boolean record;

    /**
     * 绑定信息：方便将request的信息复制给respond
     *
     * @param vo
     */
    public void bindBaseVO(OperateVO vo) {
        this.uuid = vo.uuid;
        this.deviceName = vo.deviceName;
        this.manufacturer = vo.manufacturer;
        this.deviceType = vo.deviceType;
        this.timeout = vo.timeout;
        this.operateMode = vo.operateMode;
        this.operateName = vo.operateName;
        this.param = vo.param;
        this.record = vo.record;
    }

    public void bindBaseVO(Map<String,Object> map) {
        this.uuid = (String)map.get("uuid");
        this.deviceName = (String)map.get("deviceName");
        this.manufacturer = (String)map.get("manufacturer");
        this.deviceType = (String)map.get("deviceType");
        this.timeout = (Integer)map.get("timeout");
        this.operateMode = (String)map.get("operateMode");
        this.operateName = (String)map.get("operateName");
        this.param = (Map<String, Object>)map.get("param");
        this.record = (Boolean)map.get("record");
    }
}
