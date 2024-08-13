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

package cn.foxtech.common.utils.serialport.linux.entity;

import com.sun.jna.Structure;


@Structure.FieldOrder({"c_iflag", "c_oflag", "c_cflag", "c_lflag", "c_line", "c_cc", "c_ispeed", "c_ospeed"})
public class TERMIOS extends Structure {
    public int c_iflag = 0;        /* input mode flags */
    public int c_oflag = 0;        /* output mode flags */
    public int c_cflag = 0;        /* control mode flags */
    public int c_lflag = 0;        /* local mode flags */
    public byte c_line = 0;            /* line discipline */
    public byte[] c_cc = new byte[32];        /* control characters */
    public int c_ispeed = 0;        /* input speed */
    public int c_ospeed = 0;        /* output speed */

    public TERMIOS() {
    }
}
