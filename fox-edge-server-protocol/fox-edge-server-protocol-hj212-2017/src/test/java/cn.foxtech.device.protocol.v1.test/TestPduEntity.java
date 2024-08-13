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

package cn.foxtech.device.protocol.v1.test;


import cn.foxtech.device.protocol.v1.hj212_2017.entity.PduEntity;

import java.io.UnsupportedEncodingException;

public class TestPduEntity {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "##0457QN=20210320163101890;ST=32;CN=2011;PW=123456;MN=81733553213013;Flag=4;CP=&&DataTime=20210320013400;w00000-Rtd=181.682,w00000-Flag=N;w21001-SampleTime=20210320005400,w21001-Rtd=45.160,w21001-Flag=N;w21011-SampleTime=20210320013400,w21011-Rtd=1.970,w21011-Flag=N;w21003-SampleTime=20210320013400,w21003-Rtd=53.131,w21003-Flag=N;w01018-SampleTime=20210320013400,w01018-Rtd=194.200,w01018-Flag=N;w01001-SampleTime=20210320013406,w01001-Rtd=7.496,w01001-Flag=N&&6E80\r\n";
        PduEntity pduEntity = PduEntity.decodePdu(str);
        str = PduEntity.encodePdu(pduEntity);
        pduEntity = PduEntity.decodePdu(str);

    }


}
