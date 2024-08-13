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

package cn.foxtech.common.utils.iotdb;

import org.apache.iotdb.isession.pool.SessionDataSetWrapper;
import org.apache.iotdb.rpc.IoTDBConnectionException;
import org.apache.iotdb.rpc.StatementExecutionException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws IoTDBConnectionException, StatementExecutionException {
        IoTDBSessionPool dbSession = new IoTDBSessionPool();
        dbSession.initialize("192.168.1.23", 6667, "root", "root");

        //   dbSession.createDatabase("root.tb_device_history");

        Map<String, Object> data = new HashMap<>();
        data.put("oid_1438132", 26);
        data.put("oid_1438134", 26.6);
        dbSession.insertRecord("root.tb_device_history.device_1193", System.currentTimeMillis(), data);

        List<String> keys = new ArrayList<>();
        keys.add("oid_1438132");
        keys.add("oid_1438134");
        SessionDataSetWrapper dataSet = dbSession.executeQuery("root.tb_device_history.device_1193", keys);

        List<List<Object>> rows = dbSession.readRecord(dataSet);

        dataSet = dbSession.lastDataQuery("root.tb_device_history.d1790", keys, System.currentTimeMillis());
        rows = dbSession.readRecord(dataSet);

        dbSession.deleteData("root.tb_device_history.device_1193", System.currentTimeMillis());
     //   dbSession.deleteData("root.tb_device_history.device_1193", keys, System.currentTimeMillis());

    }


}
