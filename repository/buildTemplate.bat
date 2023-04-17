#切换目录
cd ..\template
rd tar\*.* /s/q
md tar

cd dlt645\1.0.0
tar -cvf   ..\..\tar\dlt645_template_1.0.0.tar *.*
cd ..\..

cd iec104\1.0.0
tar -cvf   ..\..\tar\iec104_template_1.0.0.tar *.*
cd ..\..

cd mitsubishi.plc.fx\1.0.0
tar -cvf   ..\..\tar\mitsubishi.plc.fx_template_1.0.0.tar *.*
cd ..\..

cd modbus\1.0.0
tar -cvf   ..\..\tar\modbus_template_1.0.0.tar *.*
cd ..\..

cd omron.fins\1.0.0
tar -cvf   ..\..\tar\omron.fins_template_1.0.0.tar *.*
cd ..\..

cd snmp\1.0.0
tar -cvf   ..\..\tar\snmp_template_1.0.0.tar *.*
cd ..\..



