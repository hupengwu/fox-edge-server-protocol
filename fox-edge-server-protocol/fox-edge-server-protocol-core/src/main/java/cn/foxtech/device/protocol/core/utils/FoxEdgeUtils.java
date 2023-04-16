package cn.foxtech.device.protocol.core.utils;

import cn.foxtech.device.protocol.core.constants.FoxEdgeConstant;

import java.util.List;
import java.util.Map;

public class FoxEdgeUtils {
    /**
     * 为每条记录打上记录类型标记
     * @param recordType
     * @param recordList
     */
    public static List<Map<String, Object>> makeRecordTypeTag(String recordType, List<Map<String, Object>> recordList) {
        for (Map<String, Object> record : recordList) {
            record.put(FoxEdgeConstant.RECORD_TYPE_TAG, recordType);
        }

        return recordList;
    }
}
