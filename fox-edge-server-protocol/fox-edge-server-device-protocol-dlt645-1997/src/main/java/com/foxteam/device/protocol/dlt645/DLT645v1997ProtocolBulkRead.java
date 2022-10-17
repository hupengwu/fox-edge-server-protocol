package com.foxteam.device.protocol.dlt645;

import com.foxteam.device.protocol.core.annotation.FoxEdgeBulkOperate;
import com.foxteam.device.protocol.core.annotation.FoxEdgeDeviceType;
import com.foxteam.device.protocol.core.worker.FoxEdgeBulkOperateWorker;
import com.foxteam.device.protocol.core.entity.FoxEdgeBulkOperateEntity;
import com.foxteam.device.protocol.core.entity.FoxEdgeBulkOperateMethodEntity;
import com.foxteam.device.protocol.core.entity.FoxEdgeBulkOperateTemplateEntity;

/**
 * 控制器：不需要默认的流程控制器，告知框架用一个线程还执行这个函数
 */
@FoxEdgeDeviceType(value = "DLT645 v1997 Device", manufacturer = "Fox Edge")
public class DLT645v1997ProtocolBulkRead {
    /**
     * 流程控制
     *
     * @param bulkOperateEntity 控制器实体，控制器通过这个实体，跟框架交换数据
     */
    @FoxEdgeBulkOperate(name = "批量操作", timeout = 8000)
    public static void bulkOperate(FoxEdgeBulkOperateEntity bulkOperateEntity) {
        if (bulkOperateEntity instanceof FoxEdgeBulkOperateTemplateEntity) {
            // 按模板定义的流程进行批量操作
            FoxEdgeBulkOperateWorker.workByTemplate((FoxEdgeBulkOperateTemplateEntity) bulkOperateEntity);
        }
        if (bulkOperateEntity instanceof FoxEdgeBulkOperateMethodEntity) {
            // 自定义流程：逐个进行单步操作
            workBySteps((FoxEdgeBulkOperateMethodEntity) bulkOperateEntity);
        }
    }

    private static void workBySteps(FoxEdgeBulkOperateMethodEntity controllerEntity) {
        // 准备参数和清空数据
        controllerEntity.getOperateEntity().getParams().putAll(controllerEntity.getConfigEntity().getParams());
        controllerEntity.getValueEntity().getValues().clear();

        // 按自定义的流程进行一步步执行
        workByOneStep("读数据", "(当前)正向有功总电能", controllerEntity);
        workByOneStep("读数据", "(当前)反向有功总电能", controllerEntity);
        workByOneStep("读数据", "(当前)正向无功总电能", controllerEntity);
        workByOneStep("读数据", "(当前)反向无功总电能", controllerEntity);
        workByOneStep("读数据", "A相电压", controllerEntity);
        workByOneStep("读数据", "B相电压", controllerEntity);
        workByOneStep("读数据", "C相电压", controllerEntity);
        workByOneStep("读数据", "A相电流", controllerEntity);
        workByOneStep("读数据", "B相电流", controllerEntity);
        workByOneStep("读数据", "C相电流", controllerEntity);
        workByOneStep("读数据", "瞬时有功功率 ", controllerEntity);
        workByOneStep("读数据", "A相有功功率", controllerEntity);
        workByOneStep("读数据", "B相有功功率", controllerEntity);
        workByOneStep("读数据", "C相有功功率", controllerEntity);
        workByOneStep("读数据", "日期及周次", controllerEntity);
        workByOneStep("读数据", "时间", controllerEntity);
        workByOneStep("读数据", "电表运行状态字", controllerEntity);
        workByOneStep("读数据", "电网状态字", controllerEntity);
        workByOneStep("读数据", "周休日状态字", controllerEntity);
        workByOneStep("读数据", "表号", controllerEntity);
        workByOneStep("读数据", "用户号", controllerEntity);
        workByOneStep("读数据", "自动抄表日期", controllerEntity);
    }

    /**
     * 单步操作流程
     *
     * @param operateName       操作名称
     * @param objectName        操作对象
     * @param bulkOperateEntity 控制器参数实体
     */
    private static void workByOneStep(String operateName, String objectName, FoxEdgeBulkOperateMethodEntity bulkOperateEntity) {
        // 填写用户配置参数和模板参数
        bulkOperateEntity.getOperateEntity().getParams().put("table_name", "DLT645-1997.csv");

        // 操作和对象名称
        bulkOperateEntity.getOperateEntity().setOperateName(operateName);
        bulkOperateEntity.getOperateEntity().getParams().put("object_name", objectName);
        FoxEdgeBulkOperateWorker.workByOneStep(bulkOperateEntity);
    }
}
