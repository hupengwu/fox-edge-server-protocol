package cn.foxtech.common.domain.vo;

import java.util.List;
import java.util.Map;

/**
 * restful风格的接口
 */
public class RestFulRequestVO extends RestFulVO {
    public static RestFulRequestVO buildRequestVO(Map<String, Object> restFulRequestMap) {
        RestFulRequestVO restFulRequestVO = new RestFulRequestVO();
        restFulRequestVO.bindResVO(restFulRequestMap);

        return restFulRequestVO;
    }
}
