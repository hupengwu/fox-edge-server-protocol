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

package cn.foxtech.device.protocol.v1.hikvision.fire.core.entity;

public abstract class CtrlEntity {
    /**
     * 应用数据单元长度(2 字节):按小端格式传输，信息对象数目不能过大，应用数据单元总长度不得超过 512 字节；
     */
    public abstract int getAduLength();

    /**
     * 填写ADU长度
     *
     * @param aduLength AUD长度
     */
    public abstract void setAduLength(int aduLength);

    /**
     * 命令字(1 字节)
     */
    public abstract int getCmd();

    /**
     * 填写cmd
     *
     * @param cmd cmd长
     */
    public abstract void setCmd(int cmd);
}
