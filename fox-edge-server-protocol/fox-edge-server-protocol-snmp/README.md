# fox-edge-server-protocol-modbus

#### 介绍
modbus的通用解码器，它只需要配置数据格式，就可以解析数据

#### 软件架构
软件架构说明

#### 使用说明

1. 上海电表-获取电表常数（有功）
``` 
{
	"operate": [{
		"name": "上海电表-获取电表常数（有功）",
		"operate": "ReadHoldingRegisters",
		"encoder_param": {
			"reg_addr": "04 1F",
			"reg_cnt": "07"
		},
		"decoder_param": [{
			"status_index": 0,
			"bit_index": [1,                   2,                        3,                       4,                       6,                     "8,9",                11,                    12,               14,                 15],
			"bit_check": [">0",                ">0",                    ">0",                     ">0",                    ">0",                    ">0",                ">0",                  ">0",            ">0",               ">0"],
			"name": ["48V直流熔丝断告警状态", "用户交流熔丝断告警状态", "辅助交流熔丝断告警状态", "直流电压超限告警状态", "输出电压超限告警状态", "逆变器故障告警状态", "是否有逆变器手动停", "不同步告警状态", "系统紧急告警状态", "系统非紧急告警状态"]
		},
		{
			"status_index": 1,
			"bit_index": [1,                   3],
			"bit_check": [">0",                ">0"],
			"name": ["逆变器限流告警状态", "逆变器超温告警状态"]
		},
		{
			"status_index": 2,
			"bit_index": [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15],
			"bit_check": [">0",">0",">0",">0",">0",">0",">0",">0",">0",">0",">0",">0",">0",">0",">0",">0"],
			"name": ["逆变器01传输故障告警状态", "逆变器02传输故障告警状态", "逆变器03传输故障告警状态", "逆变器04传输故障告警状态", "逆变器05传输故障告警状态", "逆变器06传输故障告警状态", "逆变器07传输故障告警状态", "逆变器08传输故障告警状态", "逆变器09传输故障告警状态", "逆变器10传输故障告警状态", "逆变器11传输故障告警状态", "逆变器12传输故障告警状态", "逆变器13传输故障告警状态", "逆变器14传输故障告警状态", "逆变器15传输故障告警状态", "逆变器16传输故障告警状态"]
		},
		{
			"status_index": 5,
			"bit_index": [2,5,6,7,8,9,12,13],
			"bit_check": [">0",">0",">0",">0",">0",">0",">0",">0"],
			"name": ["过载告警状态", "是否工作在在线方式", "是否工作在逆变方式", "是否工作在交流方式", "负载是否由逆变器供电", "负载是否由市电供电", "是否由逆变切换到手动旁路", "是否由市电切换到手动旁路"]
		}]
	}]
}
``` 

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)