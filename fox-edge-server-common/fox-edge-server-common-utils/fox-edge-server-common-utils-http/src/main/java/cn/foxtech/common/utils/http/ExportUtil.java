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

package cn.foxtech.common.utils.http;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * 导出HTTP下载文件
 */
public class ExportUtil {
    public static void exportTextFile(HttpServletResponse response, String path, String fileName) {
        File download = new File(path + "/" + fileName);
        if (download.exists()) {
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), StandardCharsets.ISO_8859_1));

            InputStream is = null;
            ServletOutputStream os = null;
            try {
                is = new FileInputStream(download);
                os = response.getOutputStream();
                byte[] b = new byte[1024];
                int n;
                while ((n = is.read(b)) != -1) {
                    os.write(b, 0, n);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    if (os != null) {
                        os.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
