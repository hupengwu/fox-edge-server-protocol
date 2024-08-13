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

package cn.foxtech.channel.socket.core.handler;

import cn.foxtech.channel.socket.core.script.ScriptSplitMessage;
import cn.foxtech.device.protocol.v1.utils.netty.SplitMessageHandler;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Script版本的报文拆包Handler
 */
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class ScriptSplitMessageHandler extends SplitMessageHandler {

    private ScriptSplitMessage scriptSplitMessage;

    public void resetHeader() {
        this.header = new int[scriptSplitMessage.getHeaderLength()];
    }


    /**
     * 是否为非法报文：通过检查报文头部，这些协议中约定的起始标记，判定该报文是否为合法的报文
     *
     * @return 非法报文
     */
    @Override
    public boolean isInvalidPack() {
        return this.scriptSplitMessage.isInvalidPack(this.header);
    }

    /**
     * 从minPack数组中，取出报文长度信息
     *
     * @return
     */
    @Override
    public int getPackLength() {
        return this.scriptSplitMessage.getPackLength(this.header);
    }
}
