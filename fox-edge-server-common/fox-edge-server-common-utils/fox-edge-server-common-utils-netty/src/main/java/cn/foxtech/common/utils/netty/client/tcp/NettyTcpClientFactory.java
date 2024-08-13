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

package cn.foxtech.common.utils.netty.client.tcp;

import cn.foxtech.common.utils.netty.handler.SocketChannelHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Getter;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * Netty客户端工厂：创建异步连接
 */
public class NettyTcpClientFactory {

    private final static NettyTcpClientFactory instance = new NettyTcpClientFactory();

    private final Bootstrap bootstrap = new Bootstrap();

    private final EventLoopGroup group = new NioEventLoopGroup();

    @Getter
    private final NettyTcpClientInitializer channelInitializer = new NettyTcpClientInitializer();

    private NettyTcpClientFactory() {
        this.bootstrap.group(this.group)// 绑定group
                .channel(NioSocketChannel.class)// 绑定NioSocketChannel
                .option(ChannelOption.SO_KEEPALIVE, true) // 保持心跳
                .option(ChannelOption.TCP_NODELAY, true) // 立即发送
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)// 连接超时
                .handler(this.channelInitializer);
    }

    /**
     * 复用一个公共实例
     * 注意，绑定到该工厂的SocketChannelHandler，也只能是一个。
     *
     * @return
     */
    public static NettyTcpClientFactory getInstance() {
        return instance;
    }

    /**
     * 建立多个工厂，使用者自己来管理实例
     * 此时每个客户端连接，各自绑定独立的handler
     * @return
     */
    public static NettyTcpClientFactory newInstance() {
        return new NettyTcpClientFactory();
    }

    public static void main(String[] args) {
        NettyTcpClientFactory inst = NettyTcpClientFactory.getInstance();
        inst.getChannelInitializer().setChannelHandler(new SocketChannelHandler());
        inst.createClient("127.0.0.1", 8080);
        inst.createClient("127.0.0.1", 8081);
        inst.createClient("127.0.0.1", 8082);
    }

    public ChannelFuture createClient(SocketAddress remoteAddress) {
        this.bootstrap.remoteAddress(remoteAddress);
        ChannelFuture channelFuture = this.bootstrap.connect().addListener(future -> {
            if (future.cause() != null) {
                //  System.out.println(future.cause().getMessage());
            }
        });

        return channelFuture;
    }

    public ChannelFuture createClient(String host, int port) {
        SocketAddress remoteAddress = new InetSocketAddress(host, port);
        return this.createClient(remoteAddress);
    }
}
