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

package cn.foxtech.common.utils.netty.handler;

import io.netty.channel.ChannelHandlerContext;

public class SocketChannelHandler {
    /**
     * 客户端与服务端建立连接成功时，触发该动作
     *
     * @param ctx 上下文
     * @throws Exception 异常
     */
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    }

    /**
     * 从客户端收到新的数据时，这个方法会在收到消息时被调用
     *
     * @param ctx 上下文
     * @param msg 消息
     */
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
    }

    /**
     * 客户端与服务端 断连时 执行
     *
     * @param ctx 上下文
     */
    public void channelInactive(final ChannelHandlerContext ctx) {
    }

    /**
     * 当出现 Throwable 对象才会被调用，即当 Netty 由于 IO 错误或者处理器在处理事件时抛出的异常时
     *
     * @param ctx   上下文
     * @param cause 异常源头
     */
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    }
}
