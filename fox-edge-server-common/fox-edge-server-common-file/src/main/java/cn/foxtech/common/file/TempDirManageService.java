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

package cn.foxtech.common.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class TempDirManageService {
    @Value("${spring.fox-service.service.type}")
    private String foxServiceType = "undefinedServiceType";

    @Value("${spring.fox-service.service.name}")
    private String foxServiceName = "undefinedServiceName";

    private String absolutePath;

    public void createTempDir() throws IOException {
        if (this.absolutePath == null) {
            File file = new File("");
            this.absolutePath = file.getAbsolutePath();
        }


        String pathName = this.absolutePath + "/temp/" + this.foxServiceType + "/" + this.foxServiceName;
        Path path = Paths.get(pathName);

        Files.createDirectories(path);
        if (!Files.exists(path)) {
            throw new IOException("unable to create dir: " + path);
        }
    }

    /**
     * 获得临时目录
     *
     * @return 服务拥有的临时目录
     */
    public String getTempDir() {
        if (this.absolutePath == null) {
            File file = new File("");
            this.absolutePath = file.getAbsolutePath();
        }

        return this.absolutePath + "/temp/" + this.foxServiceType + "/" + this.foxServiceName;
    }
}
