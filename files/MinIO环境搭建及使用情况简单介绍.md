# MinIO
## 一、什么是MinIO?
MinIO是一个基于Apache License v2.0开源协议的对象存储服务。它兼容亚马逊S3云存储服务接口，非常适合于存储大容量非结构化的数据，例如图片、视频、日志文件、备份数据和容器/虚拟机镜像等，而一个对象文件可以是任意大小，从几kb到最大5T不等。

MinIO是一个非常轻量的服务,可以很简单的和其他应用的结合，类似 NodeJS, Redis 或者 MySQL。

## 二、MinIO环境搭建
### 1、Linux系统

#### 方式一、docker安装MinIO（推荐）

***使用环境要求：Linux系统通互联网！！！！***

##### 1）、docker安装MinIO镜像
```text
docker pull minio/minio
```
##### 2）、后台运行容器方式启动MinIO
```text
docker run -p 9000:9000 --name minio -di --restart=always \
  -e "MINIO_ACCESS_KEY=AKIAIOSFODNN7EXAMPLE" \
  -e "MINIO_SECRET_KEY=wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY" \
  -v /minio/data:/data \
  -v /minio/config:/root/.minio \
  minio/minio server /data
```
```text
说明：
# -p 端口映射  将外部端口 映射到 容器内部端口  
# --name 自定义容器名称
# -di 后台运行的方式运行
# --restart=always  一旦docker重启或者开启时，也自动启动镜像
# -e 设置系统变量  在这里是设置Minio的ACCESS_KEY和SECRET_KEY 不设置默认为“minioadmin/minioadmin”
# -v 挂载文件  将系统文件  映射到  容器内部对应的文件夹
```
access_key为**AKIAIOSFODNN7EXAMPLE**，即登录用户（唯一标识）
secret_key为**wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY**，即登录密码
##### 3）、查看是否启动成功
>1)、查看所有docker，获取名称为minio的容器ID
```text
docker ps -a
```
![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/minio/docker_ps_a.jpg)
>2)、查看MinIO的日志
```text
docker logs 容器名称（容器ID）
```
![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/minio/docker_logs.jpg)
>3)、访问MinIO
+ 登录页面
![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/minio/minio_login.jpg)
+ 首页
![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/minio/minio_index.jpg)

#### 方式二、官网下载安装包安装MinIO

***使用环境要求：Linux系统通用！！！***

##### 1）、MinIO Linux服务端下载

+ [官网地址](https://min.io/)
+ [github地址](https://github.com/minio/minio)
+ [linux 服务端下载地址](https://dl.min.io/server/minio/release/linux-amd64/minio)

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/minio/MinIO%20Linux%E4%B8%8B%E8%BD%BD.jpg)

下载成功：

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/minio/MinIO%20Linux%E5%AE%89%E8%A3%85%E5%8C%85%E4%B8%8B%E8%BD%BD%E6%88%90%E5%8A%9F.jpg)

##### 2）、安装启动

进入minio安装包所在文件夹，输入如下命令：

```text
# 授权
chmod +x minio
```

```text
# 启动运行
MINIO_ACCESS_KEY=minioadmin MINIO_SECRET_KEY=minioadmin ./minio server /minio/data
```

启动成功：（调试使用）

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/minio/MInIO%20Linux%E4%B8%AD%E5%90%AF%E5%8A%A8%E8%BF%90%E8%A1%8C.jpg)

后台一直运行命令：（实际使用）

```text
MINIO_ACCESS_KEY=minioadmin MINIO_SECRET_KEY=minioadmin nohup ./minio server /minio/data >&/minio/minio.log &
```

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/minio/MinIO%20Linux%E6%9C%8D%E5%8A%A1%E5%90%8E%E5%8F%B0%E8%BF%90%E8%A1%8C.jpg)

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/minio/MinIO%20Linux%E6%9C%8D%E5%8A%A1%E5%90%8E%E5%8F%B0%E8%BF%90%E8%A1%8C%E6%97%A5%E5%BF%97.jpg)

查看minio的运行进程：

```text
ps -ef | grep minio
```

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/minio/Linux%E4%B8%AD%E6%9F%A5%E7%9C%8BMinIO%E8%BF%9B%E7%A8%8B.jpg)

杀死minio运行进程：

```text
kill -9 进程号
```

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/minio/Linux%E4%B8%AD%E6%9D%80%E6%AD%BB%E5%90%8E%E5%8F%B0%E8%BF%90%E8%A1%8C%E7%9A%84MinIO%E6%9C%8D%E5%8A%A1.jpg)

### 2、Windows系统（官网下载安装包安装）

***使用环境要求：Windows系统通用！！！***

#### 1）、MinIO Windows服务端下载
+ [官网地址](https://min.io/)
+ [github地址](https://github.com/minio/minio)
+ [windows 服务端下载地址](https://dl.minio.io/server/minio/release/windows-amd64/minio.exe)
![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/minio/MinIO%20Windows%E4%B8%8B%E8%BD%BD.jpg)
#### 2)、安装启动
启动一个cmd窗口，进入minio.exe所在文件夹，输入如下命令：
```text
minio.exe server E:\MinIO\data
```
```text
说明："E:\MinIO\data"是MinIO文件存储本地路径。
```
安装启动成功之后，出现如下界面：

![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/minio/MinIO%20Windows%E4%B8%AD%E5%AE%89%E8%A3%85%E5%90%AF%E5%8A%A8.jpg)

#### 3)、访问MinIO
地址：http://本机IP:9000/

（注意：本人这里访问：[http://192.168.6.196:9000/](http://192.168.6.196:9000/)）
![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/minio/Windows%E9%A6%96%E9%A1%B5.jpg)

本地数据存储：
![image text](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/minio/Windows%E6%9C%AC%E5%9C%B0%E5%AD%98%E5%82%A8.jpg)


## 三、JAVA API
[官网地址](https://docs.min.io/docs/java-client-quickstart-guide.html)

>结合官网JAVA API进行项目需求开发！！！！！！！

## 四、注意事项
本人遇到的坑：
+ 服务器上的时间与本地时间相差较大，导致java程序运行报错！！！（解决方法：矫正服务器时间即可）
