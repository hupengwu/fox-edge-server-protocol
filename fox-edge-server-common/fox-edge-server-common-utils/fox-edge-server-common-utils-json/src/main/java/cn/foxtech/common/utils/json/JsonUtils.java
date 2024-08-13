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

package cn.foxtech.common.utils.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 将T1的数据填入T2结构中
     *
     * @param value
     * @param valueType
     * @param <T1>
     * @param <T2>
     * @return
     */
    public static <T1, T2> T2 buildObject(T1 value, Class<T2> valueType) throws IOException {
        String jsn = objectMapper.writeValueAsString(value);
        return objectMapper.readValue(jsn, valueType);
    }

    public static <T> T buildObject(String value, Class<T> valueType) throws IOException {
        return objectMapper.readValue(value, valueType);
    }

    public static <T> T buildObjectWithoutException(String value, Class<T> valueType) {
        try {
            return objectMapper.readValue(value, valueType);
        } catch (Exception e) {
            return null;
        }
    }

    public static Map<String, Object> buildMapWithDefault(String value, Map<String, Object> defaultValue) {
        try {
            return objectMapper.readValue(value, Map.class);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 将MAP的数据填入T的各个字段中
     *
     * @param map
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T buildObject(Map map, Class<T> valueType) throws JsonParseException {
        try {
            String jsn = objectMapper.writeValueAsString(map);
            return objectMapper.readValue(jsn, valueType);
        } catch (JsonProcessingException je) {
            throw new JsonParseException(null, je.getMessage());
        } catch (IOException ie) {
            throw new JsonParseException(null, ie.getMessage());
        }
    }

    public static <T> T buildObjectWithoutException(Map map, Class<T> valueType) {
        try {
            String jsn = objectMapper.writeValueAsString(map);
            return objectMapper.readValue(jsn, valueType);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 生成JSON字符串
     *
     * @param value
     * @param <T>
     * @return
     */
    public static <T> String buildJson(T value) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(value);
        return json;
    }

    public static <T> String buildJsonWithoutException(T value) {
        try {
            String json = objectMapper.writeValueAsString(value);
            return json;
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T clone(T value) {
        try {
            String json = objectMapper.writeValueAsString(value);
            return (T)objectMapper.readValue(json, value.getClass());
        } catch (Exception e) {
            return null;
        }
    }
}
