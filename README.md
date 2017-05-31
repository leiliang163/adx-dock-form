# 项目部署位置
web003

# 项目部署说明
## 克隆代码
```
cd ~
cd code 
git clone http://doc.mjoys.com/server/adx-dock-platform.git
```
## 编译打包
```
cd adx-dock-platform
gradle clean assemble
```
## 把打好的包放到deploy目录下
```
cd ~
rm -rf deploy/adx-dock-platform/
cp code/adx-dock-platform/adx-dock-platform-deploy/build/libs/adx-dock-platform-0.1.0.jar  deploy/adx-dock-platform/adx-dock-platform.jar
```
## 下载配置文件
```
cd ~
git clone http://doc.mjoys.com/server/config-file.git
cp config-file/adx-dock-platform/mx/adx-dock-platform.properties config/adx-dock-platform.properties
```
## 启动程序
```
cd ~
java -Xms3g -Xmx3g -Xmn1024m  -jar deploy/adx-dock-platform/adx-dock-platform.jar --spring.config.location=D:\adx-dock-platform.properties
```
## 查询日志
```
cd ~
tailf adx/logs/adx-dock-platform.log
```
# 项目停止
1. ps -aux | grep adx-dock-platform
2. kill -9 进程号


# 日志说明
1. 应用日志:用户目录/adx/logs/adx-dock-platform.log
2. 错误日志:用户目录/adx/logs/adx-dock-platform.error.log

# dubbo服务调用方
+ mjoys-database-service

# 注意事项 
+ gradle：用的是2.14