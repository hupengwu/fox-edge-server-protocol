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

package cn.foxtech.common.utils.serialport.linux;

import cn.foxtech.common.utils.serialport.linux.entity.FD_SET;
import cn.foxtech.common.utils.serialport.linux.entity.TERMIOS;
import cn.foxtech.common.utils.serialport.linux.entity.TIMEVAL;
import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * LINUX的C语言系统函数
 * 通过JNA声明LINUX系统API，使得可以直接调用LINUX操作系统API
 */
public interface LinuxAPI extends com.sun.jna.platform.unix.LibCAPI, Library {
    String NAME = "c";
    LinuxAPI INSTANCE = (LinuxAPI) Native.load("c", LinuxAPI.class);

    int open(
            String path,
            int oflag
    );

    int close(
            int fd
    );

    int tcgetattr(
            int fd,
            TERMIOS termios
    );

    void bzero(
            TERMIOS termios,
            int size
    );

    int cfsetispeed(
            TERMIOS termios,
            int speed
    );

    int cfsetospeed(
            TERMIOS termios,
            int speed
    );

    void cfmakeraw(
            TERMIOS termios
    );

    int tcflush(
            int fd,
            int queue_selector
    );

    int tcsetattr(
            int fd,
            int optional_actions,
            TERMIOS termios
    );

    int write(
            int fd,
            byte[] buf,
            int size
    );

    int select(
            int __nfds,
            FD_SET readfds,
            FD_SET writefds,
            FD_SET exceptfds,
            TIMEVAL timeout
    );

    int
    read(
            int fd,
            byte[] buf,
            long nbytes
    );
}

