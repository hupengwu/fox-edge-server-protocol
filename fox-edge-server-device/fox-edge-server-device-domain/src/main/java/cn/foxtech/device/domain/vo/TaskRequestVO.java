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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 包操作：批量操作
 */
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class TaskRequestVO extends TaskVO {
    /**
     * 操作参数:一组有序的设备请求
     */
    private List<OperateRequestVO> requestVOS = new ArrayList<>();

    /**
     * 构造单个操作的请求包
     *
     * @param operateRequestVO 操作请求
     * @param clientName
     * @return
     */
    public static TaskRequestVO buildRequestVO(OperateRequestVO operateRequestVO, String clientName) {
        TaskRequestVO taskRequestVO = new TaskRequestVO();
        taskRequestVO.setUuid(operateRequestVO.getUuid());
        taskRequestVO.setClientName(clientName);
        taskRequestVO.setTimeout(operateRequestVO.getTimeout());
        taskRequestVO.getRequestVOS().add(operateRequestVO);
        return taskRequestVO;
    }

    public static TaskRequestVO buildRequestVO(Map<String, Object> map) {
        TaskRequestVO taskRequestVO = new TaskRequestVO();
        taskRequestVO.bindBaseVO(map);


        List<Map<String, Object>> requestVOS = (List<Map<String, Object>>) map.get("requestVOS");
        for (Map<String, Object> requestMap : requestVOS) {
            OperateRequestVO requestVO = OperateRequestVO.buildOperateRequestVO(requestMap);

            taskRequestVO.requestVOS.add(requestVO);
        }

        return taskRequestVO;
    }
}
