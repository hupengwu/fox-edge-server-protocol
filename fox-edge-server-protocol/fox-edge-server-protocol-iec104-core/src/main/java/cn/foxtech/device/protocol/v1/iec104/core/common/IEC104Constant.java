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

package cn.foxtech.device.protocol.v1.iec104.core.common;

/**
 * IEC104Constant
 */
public class IEC104Constant {

	/**
	 * 开始字符
	 */
	public static final  byte  HEAD_DATA = 0x68;

	/**
	 * 控制域长度
	 */
	public static final  byte CPNTROL_LENGTH = 0x04;

	/**
	 * APCI 长度
	 */
	public static final byte APCI_LENGTH = 0x06;

	/**
	 * APCI 中 发送序号低位坐标
	 */
	public static final int ACCEPT_LOW_INDEX = 2;

	/**
	 * APCI 中 发送序号高位坐标
	 */
	public static final int ACCEPT_HIGH_INDEX = 3;


	/**
	 *最大接收序号
	 */
	public static final Short SEND_MAX = 32767;

	/**
	 * 最小接收序号
	 */
	public static final Short SEND_MIN = 0;
}
