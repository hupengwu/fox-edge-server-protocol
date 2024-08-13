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

import cn.foxtech.channel.socket.core.script.ScriptServiceKey;
import cn.foxtech.device.protocol.v1.utils.netty.ServiceKeyHandler;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Script版本的身份识别Handler
 */
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class ScriptServiceKeyHandler extends ServiceKeyHandler {
    private ScriptServiceKey scriptServiceKey;

    @Override
    public String getServiceKey(byte[] pdu) {
        return this.scriptServiceKey.getServiceKey(pdu);
    }

}
