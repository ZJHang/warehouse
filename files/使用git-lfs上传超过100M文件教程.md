# 一、gif-lfs下载安装

## 1、下载

[官网](https://git-lfs.github.com/)

![下载](E:\warehouse\images\git\git-lfs-下载-01.jpg)

## 2、安装

> 双击[安装包](https://github.com/git-lfs/git-lfs/releases/download/v2.13.2/git-lfs-windows-v2.13.2.exe)进行安装即可！！！！（注意：本人操作系统为windows）

![git-lfs-安装-02](E:\warehouse\images\git\git-lfs-安装-02.jpg)

> 安装成功，则本地安装文件！！！！

![git-lfs-安装-03](E:\warehouse\images\git\git-lfs-安装-03.jpg)

> 验证是否安装成功：查看git-lfs的版本号！！！！

```text
git-lfs version
```

结果：

![git-lfs-安装-04](E:\warehouse\images\git\git-lfs-安装-04.jpg)

# 二、使用git-lfs上传大文件

注意：这里的**大文件**指**超过100M的文件**。

## 1、第一步：本地Git仓库中初始化git-lfs环境

```text
git lfs install
```

![git-lfs-上传-05](E:\warehouse\images\git\git-lfs-上传-05.jpg)

## 2、第二步：添加git-lfs管理的文件

```text
git lfs track "大文件路径"
```

**注意：大文件路径是相对于根目录的全路径，切记，切记！！！！！**

例子：

> 确定需要上传的大文件路径！！！

![git-lfs-上传-06](E:\warehouse\images\git\git-lfs-上传-06.jpg)

> 使用命令添加需要git-lfs管理的文件

![git-lfs-上传-07](E:\warehouse\images\git\git-lfs-上传-07.png)

> 生成一个".gitattributes"文件！！！

![git-lfs-上传-08](E:\warehouse\images\git\git-lfs-上传-08.jpg)

> 将".gitattributes"提交推送至Git的远程仓库（**推送先，切记，很重要！！！**）

![git-lfs-上传-09](E:\warehouse\images\git\git-lfs-上传-09.jpg)![git-lfs-上传-10](E:\warehouse\images\git\git-lfs-上传-10.jpg)![git-lfs-上传-11](E:\warehouse\images\git\git-lfs-上传-11.jpg)![git-lfs-上传-12](E:\warehouse\images\git\git-lfs-上传-12.jpg)![git-lfs-上传-13](E:\warehouse\images\git\git-lfs-上传-13.jpg)

## 3、第三步：正常使用Git上传文件

**到这里就能正常上传大文件了，撒花！！！！**