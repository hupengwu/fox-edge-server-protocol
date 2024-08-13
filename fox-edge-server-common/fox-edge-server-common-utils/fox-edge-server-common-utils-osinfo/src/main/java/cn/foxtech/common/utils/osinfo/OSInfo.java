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

package cn.foxtech.common.utils.osinfo;

/**
 * 操作系统信息
 */
public class OSInfo {

    private static String OS = System.getProperty("os.name").toLowerCase();

    private static OSInfo _instance = new OSInfo();

    private EPlatform platform;

    private OSInfo() {
    }

    public static boolean isLinux() {

        return OS.indexOf("linux") >= 0;

    }

    public static boolean isMacOS() {

        return OS.indexOf("mac") >= 0 && OS.indexOf("os") > 0 && OS.indexOf("x") < 0;

    }

    public static boolean isMacOSX() {

        return OS.indexOf("mac") >= 0 && OS.indexOf("os") > 0 && OS.indexOf("x") > 0;

    }

    public static boolean isWindows() {

        return OS.indexOf("windows") >= 0;

    }

    public static boolean isOS2() {

        return OS.indexOf("os/2") >= 0;

    }

    public static boolean isSolaris() {

        return OS.indexOf("solaris") >= 0;

    }

    public static boolean isSunOS() {

        return OS.indexOf("sunos") >= 0;

    }

    public static boolean isMPEiX() {

        return OS.indexOf("mpe/ix") >= 0;

    }

    public static boolean isHPUX() {

        return OS.indexOf("hp-ux") >= 0;

    }

    public static boolean isAix() {

        return OS.indexOf("aix") >= 0;

    }

    public static boolean isOS390() {

        return OS.indexOf("os/390") >= 0;

    }

    public static boolean isFreeBSD() {

        return OS.indexOf("freebsd") >= 0;

    }

    public static boolean isIrix() {

        return OS.indexOf("irix") >= 0;

    }

    public static boolean isDigitalUnix() {

        return OS.indexOf("digital") >= 0 && OS.indexOf("unix") > 0;

    }

    public static boolean isNetWare() {

        return OS.indexOf("netware") >= 0;

    }

    public static boolean isOSF1() {

        return OS.indexOf("osf1") >= 0;

    }

    public static boolean isOpenVMS() {

        return OS.indexOf("openvms") >= 0;

    }

    /**
     * 获取操作系统名字
     *
     * @return 操作系统名
     */

    public static EPlatform getOSname() {

        if (isAix()) {

            _instance.platform = EPlatform.AIX;

        } else if (isDigitalUnix()) {

            _instance.platform = EPlatform.Digital_Unix;

        } else if (isFreeBSD()) {

            _instance.platform = EPlatform.FreeBSD;

        } else if (isHPUX()) {

            _instance.platform = EPlatform.HP_UX;

        } else if (isIrix()) {

            _instance.platform = EPlatform.Irix;

        } else if (isLinux()) {

            _instance.platform = EPlatform.Linux;

        } else if (isMacOS()) {

            _instance.platform = EPlatform.Mac_OS;

        } else if (isMacOSX()) {

            _instance.platform = EPlatform.Mac_OS_X;

        } else if (isMPEiX()) {

            _instance.platform = EPlatform.MPEiX;

        } else if (isNetWare()) {

            _instance.platform = EPlatform.NetWare_411;

        } else if (isOpenVMS()) {

            _instance.platform = EPlatform.OpenVMS;

        } else if (isOS2()) {

            _instance.platform = EPlatform.OS2;

        } else if (isOS390()) {

            _instance.platform = EPlatform.OS390;

        } else if (isOSF1()) {

            _instance.platform = EPlatform.OSF1;

        } else if (isSolaris()) {

            _instance.platform = EPlatform.Solaris;

        } else if (isSunOS()) {

            _instance.platform = EPlatform.SunOS;

        } else if (isWindows()) {

            _instance.platform = EPlatform.Windows;

        } else {

            _instance.platform = EPlatform.Others;

        }

        return _instance.platform;

    }

}
