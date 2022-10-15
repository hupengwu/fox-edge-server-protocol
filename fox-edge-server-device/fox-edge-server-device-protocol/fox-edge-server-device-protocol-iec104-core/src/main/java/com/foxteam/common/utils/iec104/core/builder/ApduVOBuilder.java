package com.foxteam.common.utils.iec104.core.builder;

import com.foxteam.common.utils.iec104.core.encoder.MessageUtils;
import com.foxteam.common.utils.iec104.core.encoder.ValueEncoder;
import com.foxteam.common.utils.iec104.core.entity.*;
import com.foxteam.common.utils.iec104.core.enums.FrameTypeEnum;
import com.foxteam.common.utils.iec104.core.enums.UControlTypeEnum;
import com.foxteam.common.utils.iec104.core.vo.ApduVO;

public class ApduVOBuilder {
    public static ApduEntity buildEntity(ApduVO apduVO) {
        ApduEntity apduEntity = new ApduEntity();

        ControlEntity controlVO = buildControlEntity(apduVO.getControl());
        apduEntity.setControl(controlVO);

        AsduEntity asduEntity = buildAsduEntity(apduVO.getAsdu());
        apduEntity.setAsdu(asduEntity);

        return apduEntity;
    }

    public static ApduVO buildVO(ApduEntity apduEntity) {
        ApduVO apduVO = new ApduVO();

        ApduVO.ControlVO controlVO = buildControlVO(apduEntity.getControl());
        apduVO.setControl(controlVO);

        ApduVO.AsduVO asduVO = buildAsduVO(apduEntity.getAsdu());
        apduVO.setAsdu(asduVO);

        return apduVO;
    }

    private static ControlEntity buildControlEntity(ApduVO.ControlVO controlVO) {
        if (FrameTypeEnum.I_FORMAT.name().equals(controlVO.getType())) {
            IControlEntity controlEntity = new IControlEntity();

            controlEntity.setAccept(controlVO.getAccept());
            controlEntity.setSend(controlVO.getSend());

            return controlEntity;
        }

        if (FrameTypeEnum.S_FORMAT.name().equals(controlVO.getType())) {
            SControlEntity controlEntity = new SControlEntity();

            controlEntity.setAccept(controlVO.getAccept());

            return controlEntity;
        }

        if (FrameTypeEnum.U_FORMAT.name().equals(controlVO.getType())) {
            UControlEntity controlEntity = new UControlEntity();

            UControlTypeEnum value = UControlTypeEnum.valueOf(controlVO.getCmd());
            controlEntity.setValue(value);

            return controlEntity;
        }

        return null;
    }

    private static ApduVO.ControlVO buildControlVO(ControlEntity controlEntity) {
        ApduVO.ControlVO controlVO = new ApduVO.ControlVO();
        if (controlEntity instanceof IControlEntity) {
            controlVO.setType(FrameTypeEnum.I_FORMAT.name());
            controlVO.setAccept(((IControlEntity) controlEntity).getAccept());
            controlVO.setSend(((IControlEntity) controlEntity).getSend());
        }
        if (controlEntity instanceof UControlEntity) {
            controlVO.setType(FrameTypeEnum.U_FORMAT.name());
            controlVO.setCmd(((UControlEntity) controlEntity).getValue().name());
        }
        if (controlEntity instanceof SControlEntity) {
            controlVO.setType(FrameTypeEnum.S_FORMAT.name());
            controlVO.setAccept(((SControlEntity) controlEntity).getAccept());
        }

        return controlVO;
    }

    private static AsduEntity buildAsduEntity(ApduVO.AsduVO asduVO) {
        if (asduVO == null) {
            return null;
        }

        AsduEntity asduEntity = new AsduEntity();
        asduEntity.setData(ValueEncoder.hexStringToBytes(asduVO.getData()));
        asduEntity.setCommonAddress(asduVO.getCommonAddress());
        asduEntity.setTypeId(asduVO.getTypeId());
        asduEntity.setVsq(buildVsqEntity(asduVO.getVsq()));
        asduEntity.setCot(buildCotEntity(asduVO.getCot()));

        return asduEntity;
    }

    private static ApduVO.AsduVO buildAsduVO(AsduEntity asduEntity) {
        if (asduEntity == null) {
            return null;
        }

        ApduVO.AsduVO asduVO = new ApduVO.AsduVO();
        asduVO.setData(ValueEncoder.byteArrayToHexString(asduEntity.getData()));
        asduVO.setCommonAddress(asduEntity.getCommonAddress());
        asduVO.setTypeId(asduEntity.getTypeId());
        asduVO.setVsq(buildVsqVO(asduEntity.getVsq()));
        asduVO.setCot(buildCotVO(asduEntity.getCot()));

        return asduVO;
    }

    private static VsqEntity buildVsqEntity(ApduVO.VsqVO vsqVO) {
        VsqEntity vsqEntity = new VsqEntity();
        vsqEntity.setNum(vsqVO.getNum());
        vsqEntity.setSq(vsqVO.isSq());

        return vsqEntity;
    }

    private static ApduVO.VsqVO buildVsqVO(VsqEntity vsqEntity) {
        ApduVO.VsqVO vsqVO = new ApduVO.VsqVO();
        vsqVO.setNum(vsqEntity.getNum());
        vsqVO.setSq(vsqEntity.isSq());

        return vsqVO;
    }

    private static CotEntity buildCotEntity(ApduVO.CotVO cotVO) {
        CotEntity cotEntity = new CotEntity();
        cotEntity.setPn(cotVO.isPn());
        cotEntity.setTest(cotVO.isTest());
        cotEntity.setAddr(cotVO.getAddr());
        cotEntity.setReason(cotVO.getReason());

        return cotEntity;
    }

    private static ApduVO.CotVO buildCotVO(CotEntity cotEntity) {
        ApduVO.CotVO cotVO = new ApduVO.CotVO();
        cotVO.setPn(cotEntity.isPn());
        cotVO.setTest(cotEntity.isTest());
        cotVO.setAddr(cotEntity.getAddr());
        cotVO.setReason(cotEntity.getReason());
        cotVO.setReasonMsg(MessageUtils.getReasonMessage(cotEntity.getReason()));

        return cotVO;
    }


}
