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

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class RestfulLikeRespondVO extends RestfulLikeVO {
    /**
     * 出错信息
     */
    private String msg = "";
    /**
     * 出错代码
     */
    private Integer code = HttpStatus.SUCCESS;

    public static RestfulLikeRespondVO success(String msg, RestfulLikeVO data) {
        RestfulLikeRespondVO vo = new RestfulLikeRespondVO();
        vo.bindVO(data);
        vo.code = HttpStatus.SUCCESS;
        vo.msg = msg;
        return vo;
    }

    public static RestfulLikeRespondVO success(RestfulLikeVO data) {
        return RestfulLikeRespondVO.success("", data);
    }

    public static RestfulLikeRespondVO error(int code, String msg) {
        RestfulLikeRespondVO vo = new RestfulLikeRespondVO();
        vo.code = code;
        vo.msg = msg;
        return vo;
    }

    public static RestfulLikeRespondVO error(RestfulLikeVO data, int code, String msg) {
        RestfulLikeRespondVO vo = new RestfulLikeRespondVO();
        vo.bindVO(data);
        vo.code = code;
        vo.msg = msg;
        return vo;
    }

    public static RestfulLikeRespondVO error(RestfulLikeVO data, String msg) {
        return RestfulLikeRespondVO.error(data, HttpStatus.ERROR, msg);
    }

    public static RestfulLikeRespondVO error(String msg) {
        return RestfulLikeRespondVO.error(HttpStatus.ERROR, msg);
    }

}
