# TCP Server Modbus 说明

使用一个Modbus转网络的串口服务器。目前使用 http://www.usr.cn/ 的M4 系列。

* 多串口对应到一个IP地址
* 但是一个串口对应是一个Socket连接
* 串口服务器在建立连接的时候可以发送一个字符串过来，使用该字符串作为该串口的标示
* 一个串口使用IP地址+标识的方式来进行识别，这个识别号保存在Channel 的 Port 这个字段当中。