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

package cn.foxtech.common.utils.netty.server.udp;

import cn.foxtech.common.utils.netty.handler.SocketChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import lombok.Setter;

/**
 * UDP Server Handler Class
 *
 * @author 胡海龙
 */
public class NettyUdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    @Setter
    private SocketChannelHandler channelHandler = new SocketChannelHandler();

    /**
     * 接收到UDP报文
     * 注意：UDP的地址信息，不在ctx，而是在msg这边
     *
     * @param ctx 上下文
     * @param msg 报文
     * @throws Exception 异常
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        // 通知给派生类
        this.channelHandler.channelRead0(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}