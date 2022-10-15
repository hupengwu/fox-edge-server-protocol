package com.foxteam.common.utils.iec104.core.encoder;

import com.foxteam.common.utils.iec104.core.entity.*;
import com.foxteam.common.utils.iec104.core.enums.AsduTypeIdEnum;
import com.foxteam.common.utils.iec104.core.enums.CotReasonEnum;
import com.foxteam.common.utils.iec104.core.enums.UControlTypeEnum;

public class BasicSessionEncoder {
    /**
     * S帧的确认报文：对来自从站的I帧进行确认，用于告诉从站，已经收到报文了
     *
     * @param accept
     * @return
     * @throws Exception
     */
    public static byte[] encodeSFrameRespond(short accept) throws Exception {
        SControlEntity controlEntity = new SControlEntity();
        controlEntity.setAccept(accept);

        ApduEntity apduEntity = new ApduEntity();
        apduEntity.setControl(controlEntity);
        apduEntity.setAsdu(null);

        return ApduEncoder.encodeApdu(apduEntity);
    }

    /**
     * 发送启动链路指令
     *
     * @return
     * @throws Exception
     */
    public static byte[] encodeSTARTDTByRequest() throws Exception {
        UControlEntity controlEntity = new UControlEntity();
        controlEntity.setValue(UControlTypeEnum.STARTDT);

        ApduEntity apduEntity = new ApduEntity();
        apduEntity.setControl(controlEntity);
        apduEntity.setAsdu(null);

        return ApduEncoder.encodeApdu(apduEntity);
    }

    /**
     * 响应启动链路指令
     *
     * @return
     * @throws Exception
     */
    public static ApduEntity decodeSTARTDTByRespond(byte[] pdu) throws Exception {
        return ApduEncoder.decodeApdu(pdu);
    }

    /**
     * 发送启动链路指令
     *
     * @return
     * @throws Exception
     */
    public static byte[] encodeTESTFRByRequest() throws Exception {
        UControlEntity controlEntity = new UControlEntity();
        controlEntity.setValue(UControlTypeEnum.TESTFR);

        ApduEntity apduEntity = new ApduEntity();
        apduEntity.setControl(controlEntity);
        apduEntity.setAsdu(null);

        return ApduEncoder.encodeApdu(apduEntity);
    }

    /**
     * 响应启动链路指令
     *
     * @return
     * @throws Exception
     */
    public static ApduEntity decodeTESTFRByRespond(byte[] pdu) throws Exception {
        return ApduEncoder.decodeApdu(pdu);
    }


    public static ApduEntity encodeGeneralCallAsduByRequest(short send) throws Exception {
        IControlEntity controlEntity = new IControlEntity();
        controlEntity.setSend((short) send);
        controlEntity.setAccept((short) 0);

        ApduEntity apduEntity = new ApduEntity();
        apduEntity.setControl(controlEntity);

        AsduEntity asduEntity = new AsduEntity();
        apduEntity.setAsdu(asduEntity);

        asduEntity.setTypeId(AsduTypeIdEnum.generalCall.getValue());

        VsqEntity vsq = new VsqEntity();
        vsq.setSq(false);
        vsq.setNum(0);
        asduEntity.setVsq(vsq);

        CotEntity cot = new CotEntity();
        cot.setAddr(0);
        cot.setTest(false);
        cot.setPn(true);
        cot.setReason(CotReasonEnum.active.getValue());
        asduEntity.setCot(cot);

        asduEntity.setCommonAddress(1);

        byte[] data = {0x14};
        asduEntity.setData(data);

        return apduEntity;
    }

    /**
     * 发送总召唤指令
     *
     * @return
     * @throws Exception
     */
    public static byte[] encodeGeneralCallByRequest(short send) throws Exception {
        return ApduEncoder.encodeApdu(encodeGeneralCallAsduByRequest(send));
    }

    /**
     * 响应总召唤指令
     *
     * @return
     * @throws Exception
     */
    public static ApduEntity decodeGeneralCallByRespond(byte[] pdu) throws Exception {
        ApduEntity apduEntity = ApduEncoder.decodeApdu(pdu);

        return apduEntity;
    }

    public static ApduEntity encodePowerPulseCallAsduByRequest(short send) throws Exception {
        IControlEntity controlEntity = new IControlEntity();
        controlEntity.setSend((short) send);
        controlEntity.setAccept((short) 0);

        ApduEntity apduEntity = new ApduEntity();
        apduEntity.setControl(controlEntity);

        AsduEntity asduEntity = new AsduEntity();
        apduEntity.setAsdu(asduEntity);

        asduEntity.setTypeId(AsduTypeIdEnum.powerPulseCall.getValue());

        VsqEntity vsq = new VsqEntity();
        vsq.setSq(false);
        vsq.setNum(1);
        asduEntity.setVsq(vsq);

        CotEntity cot = new CotEntity();
        cot.setAddr(0);
        cot.setTest(false);
        cot.setPn(true);
        cot.setReason(CotReasonEnum.active.getValue());
        asduEntity.setCot(cot);

        asduEntity.setCommonAddress(1);

        byte[] data = {0x00,0x00,0x00,0x45};
        asduEntity.setData(data);

        return apduEntity;
    }

}
