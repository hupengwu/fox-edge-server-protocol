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

package cn.foxtech.common.utils.influxdb;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxTable;

import java.time.Instant;
import java.util.List;

/**
 * https://www.jianshu.com/p/76e0e58a497f
 *
 * @author Jenson
 */
public class InfluxDB2Example {
    public static void main(final String[] args) {

        // You can generate a Token from the "Tokens Tab" in the UI
        String token = "2_CbvEBbE3MyP2mhPOnY3rsT904kzWK05bVi8vJNEICiVwpN-V9XhJMv12MITKZ1rhlIxjqUINSOcRjh5uFJCw==";
        String bucket = "foxedge";
        String org = "foxteam";

        // 生成客户端
        InfluxDBClient client = InfluxDBClientFactory.create("http://192.168.241.128:8086", token.toCharArray());

        // 1、使用 InfluxDB Line Protocol 写入数据
        String data = "mem,host=host1 used_percent=23.43234543";
        try (WriteApi writeApi = client.getWriteApi()) {
            writeApi.writeRecord(bucket, org, WritePrecision.NS, data);
        }

        // 2.使用 Data Point 写入数据
        Point point = Point
                .measurement("mem")
                .addTag("host", "host1")
                .addField("used_percent", 24.43234543)
                .time(Instant.now(), WritePrecision.NS);

        try (WriteApi writeApi = client.getWriteApi()) {
            writeApi.writePoint(bucket, org, point);
        }

        // 3.使用POJO类写入数据
//        Mem mem = new Mem();
//        mem.host = "host1";
//        mem.used_percent = 25.43234543;
//        mem.time = Instant.now();
//
//        try (WriteApi writeApi = client.getWriteApi()) {
//            writeApi.writeMeasurement(bucket, org, WritePrecision.NS, mem);
//        }

        // 4. 查询数据
        String query = "from(bucket: \"" + bucket + "\") |> range(start: -1h) |> filter(fn: (r) => r._measurement == \"mem\")";
        List<FluxTable> tables = client.getQueryApi().query(query, org);
        System.out.println(tables);
    }
}
