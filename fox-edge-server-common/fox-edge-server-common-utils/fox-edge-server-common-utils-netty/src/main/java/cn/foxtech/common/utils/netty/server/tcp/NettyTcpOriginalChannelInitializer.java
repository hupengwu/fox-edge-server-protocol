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

package cn.foxtech.common.utils.netty.server.tcp;

import cn.foxtech.common.utils.netty.handler.BytesToByteDecoder;
import cn.foxtech.common.utils.netty.handler.BytesToByteEncoder;
import cn.foxtech.common.utils.netty.handler.ChannelInboundHandler;
import cn.foxtech.common.utils.netty.handler.SocketChannelHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import lombok.Setter;

/**
 * 通道初始化
 */
public class NettyTcpOriginalChannelInitializer<SocketChannel> extends ChannelInitializer<Channel> {
    @Setter
    private SocketChannelHandler channelHandler = new SocketChannelHandler();


    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // 出方向：byte[]的编码器：将byte[]报文，编码成待发送的TCP数据流
        pipeline.addLast(new BytesToByteEncoder());


        // 入方向：多个ChannelHandler组成链条，按顺序进行处理

        pipeline.addLast(new BytesToByteDecoder());

        ChannelInboundHandler inboundHandler = new ChannelInboundHandler();
        inboundHandler.setChannelHandler(this.channelHandler);
        pipeline.addLast(inboundHandler);
    }
}