Open-JLBS
=========

A LBS application realized by pure java
基于位置的服务(Location Based Service，LBS)，它是通过电信移动联通等运营商的无线电通讯网络（如GSM网、CDMA网）或外部定位方式(如GPS)获取移动终端用户的位置信息（地理坐标，或大地坐标），在GIS(Geographic Information System，地理信息系统)平台的支持下，为用户提供相应服务的一种增值业务。

当前移动设备上网已是大势所趋，通过GIS，WIFI热点等定位作为移动设备的一个基础功能，LBS必然是大量手机app所必备的基础应用。
Open-JLBS是一个纯Java开发的LBS基础系统。它实现了LBS的基本功能，包括但不限于：
1.定位数据的存储，删除及查找（CRUD）
2.用户签到checkin
3.用户UGC地理位置POI数据生成
4.根据指定范围查找附近用户及POI
5.定位最后一次签到lastcheckin数据

为适应高并发及高可用性，本系统使用了ActiveMQ及Memcached进行读写及缓存操作，关于相关的开源软件，请参考以下url：
http://activemq.apache.org/
http://code.google.com/p/spymemcached

本系统特点：
1.可以方便的以spring SOA服务的方式发布
2.基于Spring3.x的高可用性系统
3.多数据库支持，目前打算实现MySQL，mongodb等
4.查找附近使用多种算法实现
5.高并发下可选同步或者异步操作

本系统下一步打算实现的功能：
1.完成多数据库支持
2.附近团购群组功能引入
3.定位例程提供
4.查找附近使用空间地理位置等多种算法实现
5.加权排序算法
