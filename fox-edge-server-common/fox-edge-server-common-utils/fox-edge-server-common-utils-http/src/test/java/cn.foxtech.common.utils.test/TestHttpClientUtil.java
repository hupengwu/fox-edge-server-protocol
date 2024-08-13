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

package cn.foxtech.common.utils.test;

import cn.foxtech.common.utils.http.HttpClientUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestHttpClientUtil {
    public static void main(String[] args) throws IOException {
        String pass = "{\"username\":\"admin\",\"password\":\"admin123\"}";
        String result1 = HttpClientUtil.executePost("http://120.25.241.120:8080/auth/login", pass);

        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2lkIjoxLCJ1c2VyX2tleSI6ImVkZmYwY2I0LTI3NTctNDUyZS05MjFkLTcyNTYyNTQ2MjFmMiIsInVzZXJuYW1lIjoiYWRtaW4ifQ.VkVh8C5bRU-MwvQZIy6PSe9VRBJR-2mNcEex0jboikrLzwyCbwmmCRwkqiAFopx1LCNIQFAZHLX5_pZueem-Wg");

        String json = "{\n" + "    \"edgeId\" : \"BFEBFBFF000906A3\",\n" + "    \"entityTypeList\" : [\"DeviceValueEntity\"]\n" + "}";

        String result = HttpClientUtil.executePost("http://120.25.241.120:8080/access/config/timestamp", json, header);
        result = HttpClientUtil.executePost("http://120.25.241.120:8080/access/config/timestamp", json, header);
        result = HttpClientUtil.executePost("http://120.25.241.120:8080/access/config/timestamp", json, header);

    }
}
