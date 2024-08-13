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


import cn.foxtech.common.constant.HttpStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 响应消息
 */
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class OperateRespondVO extends OperateRequestVO {
    public static final String data_mode = "mode";
    public static final String data_value = "value";
    public static final String data_comm_status = "commStatus";
    public static final String data_comm_status_failed_time = "commFailedTime";
    public static final String data_comm_status_success_time = "commSuccessTime";
    public static final String data_comm_status_failed_count = "commFailedCount";

    /**
     * 返回数据
     */
    private Map<String, Object> data = new HashMap<>();

    /**
     * 出错代码
     */
    private Integer code = HttpStatus.SUCCESS;

    /**
     * 出错提示信息
     */
    private String msg = "";

    public static Map<String, Object> buildCommonStatus(long commSuccessTime, long commFailedTime, int commFailedCount) {
        Map<String, Object> status = new HashMap<>();
        status.put(data_comm_status_failed_time, commFailedTime);
        status.put(data_comm_status_success_time, commSuccessTime);
        status.put(data_comm_status_failed_count, commFailedCount);
        return status;

    }

    public static OperateRespondVO error(int code, String msg) {
        OperateRespondVO vo = new OperateRespondVO();
        vo.setCode(code);
        vo.setMsg(msg);
        return vo;
    }

    public static OperateRespondVO error(String msg) {
        return OperateRespondVO.error(HttpStatus.ERROR, msg);
    }

    public void setData(Map<String, Object> valueStatus, Map<String, Object> commStatus) {
        this.getData().put(OperateRespondVO.data_value, valueStatus);
        this.getData().put(OperateRespondVO.data_comm_status, commStatus);
    }

    public void bind(OperateRespondVO other) {
        this.bindBaseVO(other);
        this.setData(other.data);
        this.setMsg(other.msg);
        this.setCode(other.code);
    }

    public static OperateRespondVO buildOperateRespondVO(Map<String,Object> map){
        OperateRespondVO operateRespondVO = new OperateRespondVO();
        operateRespondVO.bindBaseVO(map);

        operateRespondVO.setCode((Integer)map.get("code"));
        operateRespondVO.setMsg((String)map.get("msg"));
        operateRespondVO.setData((Map<String,Object>)map.get("data"));

        return operateRespondVO;
    }
}
