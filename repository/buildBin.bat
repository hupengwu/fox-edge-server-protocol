#切换目录
cd ..\jar\decoder
rd tar\*.* /s/q
md tar

tar -cvf   hutool-core-5.8.3.tar  hutool-core-5.8.3.jar
tar -cvf   fox-edge-server-protocol-core-1.0.0.tar  fox-edge-server-protocol-core-1.0.0.jar
tar -cvf   fox-edge-server-protocol-telecom-core-1.0.0.tar  fox-edge-server-protocol-telecom-core-1.0.0.jar
tar -cvf   fox-edge-server-protocol-modbus-core-1.0.0.tar  fox-edge-server-protocol-modbus-core-1.0.0.jar
tar -cvf   fox-edge-server-protocol-iec104-core-1.0.0.tar  fox-edge-server-protocol-iec104-core-1.0.0.jar
tar -cvf   fox-edge-server-protocol-modbus-1.0.0.tar  fox-edge-server-protocol-modbus-1.0.0.jar
tar -cvf   fox-edge-server-protocol-gdana-digester-1.0.0.tar  fox-edge-server-protocol-gdana-digester-1.0.0.jar
tar -cvf   fox-edge-server-protocol-dlt645-core-1.0.0.tar  fox-edge-server-protocol-dlt645-core-1.0.0.jar
tar -cvf   fox-edge-server-protocol-iec104-slaver-1.0.0.tar  fox-edge-server-protocol-iec104-slaver-1.0.0.jar
tar -cvf   fox-edge-server-protocol-omron-fins-core-1.0.0.tar  fox-edge-server-protocol-omron-fins-core-1.0.0.jar
tar -cvf   fox-edge-server-protocol-mitsubishi-plc-fx-1.0.0.tar  fox-edge-server-protocol-mitsubishi-plc-fx-1.0.0.jar
tar -cvf   fox-edge-server-protocol-snmp-1.0.0.tar  fox-edge-server-protocol-snmp-1.0.0.jar
tar -cvf   fox-edge-server-protocol-cjt188-1.0.0.tar  fox-edge-server-protocol-cjt188-1.0.0.jar
tar -cvf   fox-edge-server-protocol-mitsubishi-plc-fx-core-1.0.0.tar  fox-edge-server-protocol-mitsubishi-plc-fx-core-1.0.0.jar
tar -cvf   fox-edge-server-protocol-dlt645-1997-1.0.0.tar  fox-edge-server-protocol-dlt645-1997-1.0.0.jar
tar -cvf   fox-edge-server-protocol-zxdu58-1.0.0.tar  fox-edge-server-protocol-zxdu58-1.0.0.jar
tar -cvf   fox-edge-server-protocol-cjt188-core-1.0.0.tar  fox-edge-server-protocol-cjt188-core-1.0.0.jar
tar -cvf   fox-edge-server-protocol-lrw-1.0.0.tar  fox-edge-server-protocol-lrw-1.0.0.jar
tar -cvf   fox-edge-server-protocol-cetups-1.0.0.tar  fox-edge-server-protocol-cetups-1.0.0.jar
tar -cvf   fox-edge-server-protocol-bass260zj-1.0.0.tar  fox-edge-server-protocol-bass260zj-1.0.0.jar
tar -cvf   fox-edge-server-protocol-omron-fins-1.0.0.tar  fox-edge-server-protocol-omron-fins-1.0.0.jar
tar -cvf   fox-edge-server-protocol-anno-1.0.0.tar  fox-edge-server-protocol-anno-1.0.0.jar
tar -cvf   fox-edge-server-protocol-shmeter-1.0.0.tar  fox-edge-server-protocol-shmeter-1.0.0.jar

copy *.tar tar/a/y
del *.tar


