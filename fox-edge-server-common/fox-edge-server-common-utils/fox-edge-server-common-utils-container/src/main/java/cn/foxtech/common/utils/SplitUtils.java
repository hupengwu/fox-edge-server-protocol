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

package cn.foxtech.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SplitUtils {
    public static <T> List<List<T>> split(Collection<T> dataList, int pageSize) {
        List<List<T>> resultList = new ArrayList<>();

        List<T> page = new ArrayList<>();
        for (T data : dataList) {
            if (page.size() < pageSize) {
                page.add(data);
            } else {
                resultList.add(page);
                page = new ArrayList<>();
            }
        }

        if (!page.isEmpty()) {
            resultList.add(page);
        }

        return resultList;
    }
}
