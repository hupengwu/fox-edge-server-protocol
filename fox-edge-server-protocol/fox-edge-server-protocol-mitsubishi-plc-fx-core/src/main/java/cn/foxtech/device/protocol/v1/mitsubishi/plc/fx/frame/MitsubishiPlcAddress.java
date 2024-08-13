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

package cn.foxtech.device.protocol.v1.mitsubishi.plc.fx.frame;

public class MitsubishiPlcAddress {
    public static final int PLC_D_Base_AddRess = 4096;
    public static final int PLC_D_Special_Base_AddRess = 3584;
    public static final int PLC_Y_Group_Base_AddRess = 160;
    public static final int PLC_PY_Group_Base_AddRess = 672;
    public static final int PLC_T_Group_Base_AddRess = 192;
    public static final int PLC_OT_Group_Base_AddRess = 704;
    public static final int PLC_RT_Group_Base_AddRess = 1216;
    public static final int PLC_M_SINGLE_Base_AddRess = 2048;//(命令为7.或8.时)
    public static final int PLC_M_Group_Base_AddRess = 256;
    public static final int PLC_PM_Group_Base_AddRess = 768;
    public static final int PLC_S_Group_Base_AddRess = 0;
    public static final int PLC_X_Group_Base_AddRess = 128;
    public static final int PLC_C_Group_Base_AddRess = 448;
    public static final int PLC_OC_Group_Base_AddRess = 960;
    public static final int PLC_RC_Group_Base_AddRess = 1472;
    public static final int PLC_TV_Group_Base_AddRess = 2048;
    public static final int PLC_CV16_Group_Base_AddRess = 2560;
    public static final int PLC_CV32_Group_Base_AddRess = 3072;
}
