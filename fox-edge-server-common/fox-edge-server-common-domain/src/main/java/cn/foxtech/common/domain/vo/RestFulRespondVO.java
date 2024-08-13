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

package cn.foxtech.common.domain.vo;

import cn.foxtech.common.constant.HttpStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * restful风格的接口
 */
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class RestFulRespondVO extends RestFulVO {
    /**
     * 出错信息
     */
    private String msg = "";

    /**
     * 出错代码
     */
    private Integer code = HttpStatus.SUCCESS;

    public static RestFulRespondVO buildVO(Map<String, Object> map) {
        RestFulRespondVO vo = new RestFulRespondVO();
        vo.bindResVO(map);

        vo.msg = (String) map.get("msg");
        vo.code = (Integer) map.get("code");

        return vo;
    }

    public static RestFulRespondVO success(String msg, RestFulVO data) {
        RestFulRespondVO vo = new RestFulRespondVO();
        vo.bindResVO(data);
        vo.code = HttpStatus.SUCCESS;
        vo.msg = msg;
        return vo;
    }

    public static RestFulRespondVO success(RestFulVO data) {
        return RestFulRespondVO.success("", data);
    }

    public static RestFulRespondVO error(int code, String msg) {
        RestFulRespondVO vo = new RestFulRespondVO();
        vo.code = code;
        vo.msg = msg;
        return vo;
    }

    public static RestFulRespondVO error(RestFulVO data, int code, String msg) {
        RestFulRespondVO vo = new RestFulRespondVO();
        vo.bindResVO(data);
        vo.code = code;
        vo.msg = msg;
        return vo;
    }

    public static RestFulRespondVO error(RestFulVO data, String msg) {
        return RestFulRespondVO.error(data, HttpStatus.ERROR, msg);
    }

    public static RestFulRespondVO error(String msg) {
        return RestFulRespondVO.error(HttpStatus.ERROR, msg);
    }
}
