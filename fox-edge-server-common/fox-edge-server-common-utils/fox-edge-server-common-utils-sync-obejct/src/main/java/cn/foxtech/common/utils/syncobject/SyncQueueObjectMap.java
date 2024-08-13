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

package cn.foxtech.common.utils.syncobject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class SyncQueueObjectMap {
    /**
     * 实例化对象
     */
    private static final SyncQueueObjectMap inst = new SyncQueueObjectMap();

    /**
     * list容器
     */
    private final Map<String, List<Object>> map = new ConcurrentHashMap<>();

    /**
     * 进程空间内的唯一实例对象
     *
     * @return 进程空间类的唯一实例
     */
    public static SyncQueueObjectMap inst() {
        return inst;
    }

    public List<Object> popup() throws InterruptedException {
        return this.popup("");
    }

    public List<Object> popup(String name) throws InterruptedException {
        return this.popup(name, false);
    }

    public List<Object> popup(String name, boolean one) throws InterruptedException {
        return this.popup(name, one, 100);
    }

    public List<Object> popup(String name, boolean one, int timeout) throws InterruptedException {
        List<Object> list = getList(name);

        synchronized (list) {
            // 等待消息别的线程的notify
            list.wait(timeout);

            List<Object> result = new ArrayList<>();
            if (list.isEmpty()) {
                return result;
            }

            // 弹出一个或者全部数据
            if (one) {
                result.add(list.remove(0));
            } else {
                result.addAll(list);
                list.clear();
            }

            return result;
        }
    }


    private synchronized List<Object> getList(String name) {
        List<Object> list = this.map.get(name);
        if (list == null) {
            list = new CopyOnWriteArrayList<>();
            this.map.put(name, list);
        }

        return list;
    }

    public void push(Object object) {
        this.push("", object, 1000);
    }

    public void push(Object object, int max) {
        this.push("", object, max);
    }

    public void push(String name, Object object, int max) {
        List<Object> list = getList(name);

        synchronized (list) {
            if (list.size() < max) {
                list.add(object);
            }

            // 发出notify
            list.notify();
        }
    }
}
