# 一、GraphicsMagick的简单介绍

​		GraphicsMagick号称图像处理领域的瑞士军刀。 短小精悍的代码却提供了一个鲁棒、高效的工具和库集合，来处理图像的读取、写入和操作，支持超过88中图像格式，包括重要的DPX、GIF、JPEG、JPEG-2000、PNG、PDF、PNM和TIFF。

​		GraphicsMagick是从 ImageMagick 5.5.2 分支出来的，但是现在他变得更稳定和优秀，GM更小更容易安装、GM更有效率、GM的手册非常丰富GraphicsMagick的命令与ImageMagick基本是一样的。

# 二、GraphicsMagick的安装

## 1、官网地址

[官网地址](http://www.graphicsmagick.org/)

## 2、下载地址

[下载地址](https://sourceforge.net/projects/graphicsmagick/files/latest/download)

![安装包下载](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/GraphicsMagick/GraphicsMagick%E5%AE%89%E8%A3%85%E5%8C%85%E4%B8%8B%E8%BD%BD%E9%A1%B5%E9%9D%A2.jpg)

![安装包下载成功](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/GraphicsMagick/GraphicsMagick%E5%AE%89%E8%A3%85%E5%8C%85%E4%B8%8B%E8%BD%BD%E6%88%90%E5%8A%9F.jpg)

## 3、安装

> 双击安装包开始安装，一直下一步即可！！！！

![安装成功](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/GraphicsMagick/GraphicsMagick%E5%AE%89%E8%A3%85%E6%88%90%E5%8A%9F%E9%A1%B5%E9%9D%A2.jpg)

> 验证GraphicsMagick是否安装成功呢？

```text
gm convert 源文件名称 目标文件名称
```

示例：

```text
gm convert A1.tif B1.jpg
```

![验证](https://raw.githubusercontent.com/ZJHang/warehouse/main/images/GraphicsMagick/GraphicsMagick%E5%AE%89%E8%A3%85%E6%88%90%E5%8A%9F%E9%AA%8C%E8%AF%81%E9%A1%B5%E9%9D%A2.png)

# 三、结合im4java使用

## 1、引入包

```text
<!-- https://mvnrepository.com/artifact/org.im4java/im4java -->
<dependency>
  <groupId>org.im4java</groupId>
  <artifactId>im4java</artifactId>
  <version>1.4.0</version>
</dependency>
```

## 2、工具类（流形式）

```java
package com.zjhang.util;

import org.im4java.core.*;
import org.im4java.process.Pipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName GraphicsMagicTest
 * @Description 图片处理工具GraphicsMagic功能工具类（流形式）
 * @Author zhengjh
 * @Date 2021/1/14 9:29
 * @Version 1.0
 **/
public class GraphicsMagicUtil {
    // 日志
    private static Logger logger = LoggerFactory.getLogger(GraphicsMagicUtil.class);

    // GraphicsMagic的安装路径
    private static final String BIN_PATH = "E:\\Program Files\\GraphicsMagick-1.3.36-Q16";


    /**
     * 压缩图片
     *
     * @param sourceInputStream 源文件流
     * @param imageType         图片类型
     * @param width             压缩宽度
     * @param height            压缩高度
     * @return
     */
    public static byte[] compressImage(InputStream sourceInputStream, String imageType, double width, double height) {
        try {
            Map<String, String> optionsMap = new HashMap<>();
            String widHeight = width + "x" + height;
            optionsMap.put("-scale", widHeight);// 按照给定比例缩放图片
            optionsMap.put("-gravity", "center"); // 缩放参考位置 对图像进行定位
            optionsMap.put("-extent", width + "x" + height); // 限制JPEG文件的最大尺寸
            optionsMap.put("+profile", "*");// 去除Exif信息

            return getByte(sourceInputStream, imageType, optionsMap);
        } catch (Exception e) {
            logger.info("压缩图片失败：{}", e.getMessage());
            return null;
        }
    }


    /**
     * 根据坐标裁剪图片
     *
     * @param sourceInputStream 源图片流
     * @param imageType         图片类型
     * @param x                 起始横坐标
     * @param y                 起始纵坐标
     * @param x1                结束横坐标
     * @param y1                结束纵坐标
     * @return
     */
    public static byte[] cutImage(InputStream sourceInputStream, String imageType, int x, int y, int x1, int y1) {
        try {
            int width = x1 - x;
            int height = y1 - y;
            Map<String, String> optionsMap = new HashMap<>();
            String resize = width + "x" + height + "^";
            String crop = width + "x" + height + "+" + x + "+" + y;
            optionsMap.put("-resize", resize); // 图片裁剪为宽度不超过(width)px，高度不超过(height)px的缩略图
            optionsMap.put("-crop", crop); // 严格裁剪生成大小为 (width)x(height)
            optionsMap.put("-gravity", "center"); // 缩放参考位置 对图像进行定位
            optionsMap.put("+profile", "*");// 去除Exif信息

            return getByte(sourceInputStream, imageType, optionsMap);
        } catch (Exception e) {
            logger.info("根据坐标裁剪图片失败：{}", e.getMessage());
            return null;
        }
    }

    /**
     * 图片格式转换
     *
     * @param sourceInputStream 源文件流
     * @param toFormatImageType 转换后的图片格式
     * @param optionsMap        图片处理参数（质量等）
     * @return
     */
    private static byte[] convertFormat(InputStream sourceInputStream, String toFormatImageType, Map<String, String> optionsMap) {
        try {
            return getByte(sourceInputStream, toFormatImageType, optionsMap);
        } catch (Exception e) {
            logger.info("图片格式转换失败：{}", e.getMessage());
            return null;
        }
    }

    /**
     * 添加文字水印
     *
     * @param sourceInputStream 源图片文件流
     * @param imageType         图片格式
     * @param waterText         水印文字内容
     * @return
     * @throws Exception
     */
    public static byte[] addImgText(InputStream sourceInputStream, String imageType, String waterText) {
        try {
            Map<String, String> optionsMap = new HashMap<>();

            waterText = "text 100,100 '" + waterText + "'";
            optionsMap.put("-font", "宋体"); // 添加水印字体
            optionsMap.put("-pointsize", "30"); // 水印文字大小
            optionsMap.put("-fill", "#BCBFC8");// 水印文字颜色
            optionsMap.put("-draw", waterText);// 水印文字内容

            return getByte(sourceInputStream, imageType, optionsMap);
        } catch (Exception e) {
            logger.info("添加文字水印：{}", e.getMessage());
            return null;
        }
    }

    /**
     * 获取处理之后的图片二进制流
     *
     * @param sourceInputStream
     * @param imageType
     * @param optionsMap
     * @return
     * @throws InterruptedException
     * @throws IOException
     * @throws IM4JavaException
     */
    public static byte[] getByte(InputStream sourceInputStream, String imageType, Map<String, String> optionsMap) throws InterruptedException, IOException, IM4JavaException {
        IMOperation imOperation = buildIMOperation(imageType, optionsMap);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Pipe pipeIn = new Pipe(sourceInputStream, null);
        Pipe pipeOut = new Pipe(null, byteArrayOutputStream);

        ImageCommand convertCmd = getImageCommand(CommandType.convert);
        convertCmd.setInputProvider(pipeIn);
        convertCmd.setOutputConsumer(pipeOut);

        convertCmd.run(imOperation);

        return byteArrayOutputStream.toByteArray();
    }

    /**
     * 创建 IMOperation
     *
     * @param imageType  图片类型，示例：jpg、png等
     * @param optionsMap 图片处理参数
     * @return
     */
    private static IMOperation buildIMOperation(String imageType, Map<String, String> optionsMap) {
        IMOperation imOperation = new IMOperation();

        imOperation.addImage("-");

        if (optionsMap != null && !optionsMap.isEmpty()) {
            for (Map.Entry<String, String> entry : optionsMap.entrySet()) {
                imOperation.addRawArgs(entry.getKey(), entry.getValue());
            }
        }

        imOperation.addImage(imageType + ":-");

        return imOperation;
    }

    /**
     * 获取 ImageCommand
     *
     * @param command 命令类型
     * @return
     */
    private static ImageCommand getImageCommand(CommandType command) {
        ImageCommand cmd = null;
        switch (command) {
            case convert:
                cmd = new ConvertCmd(true);
                break;
            case identify:
                cmd = new IdentifyCmd(true);
                break;
            case compositecmd:
                cmd = new CompositeCmd(true);
                break;
        }

        cmd.setSearchPath(BIN_PATH);

        return cmd;
    }

    /**
     * 定义命令类型内部类
     */
    private enum CommandType {
        convert("转换处理"),
        identify("图片信息"),
        compositecmd("图片合成");

        private String name;

        CommandType(String name) {
            this.name = name;
        }
    }

    /**
     * 测试
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO 自行进行测试
    }
}

```

