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

package cn.foxtech.iot.common.remote;

import cn.foxtech.common.entity.manager.RedisConsoleService;
import cn.foxtech.common.utils.http.HttpClientUtils;
import cn.hutool.http.HttpResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Getter(value = AccessLevel.PUBLIC)
public class RemoteHttpProxyService {
    private static final Logger logger = Logger.getLogger(RemoteHttpProxyService.class);

    /**
     * header
     */
    private final Map<String, String> header = new ConcurrentHashMap<>();
    /**
     * 服务
     */
    @Setter
    private String uri = "http://demo.thingsboard.io/api";

    @Autowired
    private RedisConsoleService consoleService;


    public int executeRestful(String res, String method, String requestJson) throws IOException {
        if (this.header.isEmpty()) {
            this.header.put("Content-Type", "application/json");
        }


        HttpResponse response = HttpClientUtils.executeRestful(this.uri + res, method, this.header, requestJson);
        return response.getStatus();
    }
}
