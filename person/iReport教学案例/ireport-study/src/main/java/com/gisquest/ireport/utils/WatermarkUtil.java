package com.gisquest.ireport.utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;

/**
 * @ClassName WatermarkUtil
 * @Description PDF使用itextpdf添加水印工具类
 * @Author zhengjh
 * @Date 2021/3/1 9:49
 * @Version 1.0
 **/
public class WatermarkUtil {
    /**
     * 增加文字水印
     *
     * @param bytes
     */
    public static byte[] addWater(byte[] bytes, String xzqname) {

        ByteArrayOutputStream bos = null;
        PdfReader reader = null;
        PdfStamper stamper = null;
        byte[] waterBytes = null;
        try {
            // 待加水印的文件
            reader = new PdfReader(bytes);
            bos = new ByteArrayOutputStream();

            // 加完水印的文件(注意：这里我将之前生成的report4.pdf文件覆盖了)
            stamper = new PdfStamper(reader, bos);
            // 获取总页数
            int total = reader.getNumberOfPages() + 1;
            PdfContentByte content;
            // 设置字体
            BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            // 水平距离
            int h = 50;
            // 垂直距离
            int v = -10;
            // 循环对每页插入水印
            for (int i = 1; i < total; i++) {
                // 每页开始，重置垂直距离
                v = 0;
                // 每页插入6行水印
                for (int n = 0; n < 4; n++) {
                    // 每行开始，重置水平距离
                    h = 60;
                    // 每行插入4列水印
                    for (int m = 0; m < 3; m++) {
                        // 在内容下层加水印（注意：此时电子签名关键字定位会出问题）
                        // content = stamper.getUnderContent(i);
                        // 在内容上层加水印（注意：电子签名关键字定位正常）
                        content = stamper.getOverContent(i);
                        // 开始
                        content.beginText();
                        // 设置颜色
                        content.setColorFill(BaseColor.GRAY);
                        // 设置字体及字号
                        content.setFontAndSize(base, 20);
                        // 设置透明度为0.4
                        PdfGState gs = new PdfGState();
                        gs.setFillOpacity(0.4f);
                        content.setGState(gs);
                        // 开始写入水印
                        //参数：alignment:左、右、居中(ALIGN_LEFT、ALIGN_RIGHT、ALIGN_CENTER); text:要输出的文本; x:文本输入的X坐标; y:文本输入的Y坐标; rotation:文本的旋转角度;
                        content.showTextAligned(Element.ALIGN_LEFT, xzqname, h, v, 45);
                        // 循环一次水平向右移动160像素
                        h = h + 180;
                        // 结束
                        content.endText();
                    }
                    // 循环一次垂直向下移动150像素
                    v = v + 225;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (stamper != null) {
                try {
                    stamper.close();
                } catch (Exception ex) {
                }
            }
            if (reader != null) {
                reader.close();
            }
            if (bos != null) {
                try {
                    waterBytes = bos.toByteArray();
                    bos.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return waterBytes;
    }
}
