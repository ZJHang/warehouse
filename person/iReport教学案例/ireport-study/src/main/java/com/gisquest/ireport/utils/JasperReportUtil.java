package com.gisquest.ireport.utils;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class JasperReportUtil {

    /**
     * 定义了报表输出类型，固定了可输出类型
     */
    public enum OutFileType {
        PDF, DOCX, HTML, XLS, XLSX, XML, RTF, CSV, TXT
    }

    /**
     * 编译报表模板文件jrxml，生成jasper二进制文件
     *
     * @param jrxmlPath
     * @param jasperPath
     * @throws JRException
     */
    private static void complieJrxml(String jrxmlPath, String jasperPath)
            throws JRException {
        JasperCompileManager.compileReportToFile(jrxmlPath, jasperPath);
    }


    private static JasperPrint getJasperPrint(String jasperReportPath, Map parameter, JRDataSource dataSource) throws JRException {
        return JasperFillManager.fillReport(jasperReportPath, parameter, dataSource);
    }


    /**
     * 通过传入List类型数据源获取JasperPrint实例
     *
     * @param jasperReportPath
     * @param outParameter
     * @param dataSource
     * @return
     * @throws JRException
     */
    private static JasperPrint getJasperPrint(String jasperReportPath, Map<String,Object> outParameter, List dataSource)
            throws JRException {
        JasperPrint jasperPrint = null;
        if (dataSource == null||dataSource.isEmpty()) {
            jasperPrint = JasperFillManager.fillReport(jasperReportPath, outParameter, new JREmptyDataSource());
        } else {
            JRBeanCollectionDataSource beanDataSource = new JRBeanCollectionDataSource(dataSource);
            //3.通过JasperFillManager工具进行填充报表，填充成功后会生成一个JasperPring文件，该文件用于输出
            jasperPrint = JasperFillManager.fillReport(jasperReportPath, outParameter, beanDataSource);
        }
        return jasperPrint;
    }

    public static void printJasperReport(String jasperReportPath, Map<String,Object> outParameter, List dataSource, Boolean withPrintDialog) {
        try {
            JasperPrint jasperPrint = getJasperPrint(jasperReportPath, outParameter, dataSource);
            //替换掉导出的代码，true为弹出打印机设置，false为直接打印
            JasperPrintManager.printReport(jasperPrint, true);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    /**
     * 传入类型，获取输出器
     *
     * @param outFileType
     * @return
     */
    private static JRAbstractExporter getJRExporter(OutFileType outFileType) {
        JRAbstractExporter exporter = null;
        if (OutFileType.PDF == outFileType) {
            exporter = new JRPdfExporter();
        } else if (OutFileType.DOCX == outFileType) {
            exporter = new JRDocxExporter();
        } else if (OutFileType.HTML == outFileType) {
            exporter = new HtmlExporter();
        } else if (OutFileType.XLS == outFileType) {
            exporter = new JRXlsExporter();
        } else if (OutFileType.XLSX == outFileType) {
            exporter = new JRXlsxExporter();
        } else if (OutFileType.XML == outFileType) {
            exporter = new JRXmlExporter();
        } else if (OutFileType.RTF == outFileType) {
            exporter = new JRRtfExporter();
        } else if (OutFileType.CSV == outFileType) {
            exporter = new JRCsvExporter();
        } else if (OutFileType.TXT == outFileType) {
            exporter = new JRTextExporter();
        }
        return exporter;
    }


    /**
     * 获得相应类型的Content type
     *
     * @param outFileType
     * @return
     */
    public static String getContentType(OutFileType outFileType) {
        String contentType = "text/html";
        switch (outFileType) {
            case PDF:
                contentType = "application/pdf";
                break;
            case XLSX:
            case XLS:
                contentType = "application/vnd.ms-excel";
                break;
            case XML:
                contentType = "text/xml";
                break;
            case RTF:
                contentType = "application/rtf";
                break;
            case DOCX:
                contentType = "application/msword";
                break;
            case CSV:
                contentType = "text/plain";
                break;
        }
        return contentType;
    }


    /**
     * 把报表转换成对应的指定文件流
     *
     * @param fileType
     * @param jasperReportPath
     * @param outParameter
     * @param dataSource
     * @param outputStream
     */
    public static void jasperToFileStream(OutFileType fileType, String jasperReportPath, Map outParameter, List dataSource, OutputStream outputStream) {

        try {
            if (outputStream == null) {
                outputStream = new ByteArrayOutputStream();
            }
            JasperPrint jasperPrint = getJasperPrint(jasperReportPath, outParameter, dataSource);
            JRAbstractExporter exporter = getJRExporter(fileType);
            //ByteArrayOutputStream baos = new ByteArrayOutputStream();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            //File exportReportFile = new File(fileName + ".docx");
            //导出文件到指定路径
            //exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportReportFile));
            if (OutFileType.HTML == fileType) {
                //导出文件到指定流
                exporter.setExporterOutput(new SimpleHtmlExporterOutput(outputStream));
                SimpleHtmlExporterConfiguration configuration = new SimpleHtmlExporterConfiguration();
                exporter.setConfiguration(configuration);

            } else {
                //String pdfFilePath = servletContext.getRealPath("/jasper/template.pdf");
                //输出到硬盘上
                //exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfFilePath));
                //输出到响应流，展现在浏览器上
                //导出文件到指定流
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
                if (OutFileType.XLS == fileType) {
                    SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
//                    configuration.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
//                    configuration.setRemoveEmptySpaceBetweenColumns(Boolean.FALSE);
//                    configuration.setOnePagePerSheet(Boolean.FALSE);
//                    configuration.setDetectCellType(Boolean.TRUE);
//                    configuration.setWhitePageBackground(Boolean.FALSE);
//                    configuration.setCollapseRowSpan(Boolean.FALSE);
//                    configuration.setFontSizeFixEnabled(Boolean.TRUE);
                    exporter.setConfiguration(configuration);
                } else if (OutFileType.XLSX == fileType) {
                    SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
                    //configuration.setMaxRowsPerSheet(DefaultConstants.MAX_NUM_EXCEL);
//                    configuration.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
//                    configuration.setRemoveEmptySpaceBetweenColumns(Boolean.FALSE);
//                    configuration.setOnePagePerSheet(Boolean.FALSE);
//                    configuration.setDetectCellType(Boolean.TRUE);
//                    configuration.setWhitePageBackground(Boolean.FALSE);
//                    configuration.setCollapseRowSpan(Boolean.FALSE);
//                    configuration.setFontSizeFixEnabled(Boolean.TRUE);
                    exporter.setConfiguration(configuration);
                } else if (OutFileType.PDF == fileType) {
                    SimplePdfReportConfiguration configuration = new SimplePdfReportConfiguration();
                    //configuration.setPermissions(PdfWriter.AllowCopy | PdfWriter.AllowPrinting);

                    exporter.setConfiguration(configuration);
                } else if(OutFileType.DOCX == fileType){
                    SimpleDocxExporterConfiguration configuration = new SimpleDocxExporterConfiguration();
                    exporter.setConfiguration(configuration);
                }
            }
            exporter.exportReport();
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    /**
     * 把报表转换成对应的指定文件格式二进制
     *
     * @param fileType
     * @param jasperReportPath
     * @param outParameter
     * @param dataSource
     * @param isAddWater
     * @param waterContent
     * @return
     */
    public static byte[] jasperToFileByte(OutFileType fileType, String jasperReportPath, Map outParameter,
                                          List dataSource, Boolean isAddWater, String waterContent) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        jasperToFileStream(fileType, jasperReportPath, outParameter, dataSource, baos);
        byte[] bytes = baos.toByteArray();
        if (isAddWater) { // 开启添加水印
            bytes = WatermarkUtil.addWater(bytes, waterContent);
        }
        return bytes;
    }


    /**
     * 显示PDF,将流写到响应中
     *
     * @param response
     * @param request
     * @param jasperReportPath
     * @param outParameter
     * @param dataSource
     * @param isAddWater
     * @param waterContent
     */
    public static void showPdf(HttpServletResponse response, HttpServletRequest request, String jasperReportPath,
                               Map outParameter, List dataSource, Boolean isAddWater, String waterContent) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");
            response.setContentType("application/pdf");
            ServletOutputStream ouputStream = response.getOutputStream();
            if (isAddWater) { // 开启添加水印
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                jasperToFileStream(OutFileType.PDF, jasperReportPath, outParameter, dataSource, bos);
                byte[] bytes = bos.toByteArray();
                bytes = WatermarkUtil.addWater(bytes, waterContent);
                IOUtils.write(bytes, ouputStream);
                ouputStream.flush();
                ouputStream.close();
            } else {
                jasperToFileStream(OutFileType.PDF, jasperReportPath, outParameter, dataSource, ouputStream);
                ouputStream.flush();
                ouputStream.close();
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
    /**
     * 显示docx,将流写到响应中
     * @param response
     * @param request
     * @param jasperReportPath
     * @param outParameter
     * @param dataSource
     */
    public static void showDocx(HttpServletResponse response, HttpServletRequest request,
                               String jasperReportPath, Map outParameter, List dataSource) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");
            response.setContentType("application/msword");
            ServletOutputStream ouputStream = response.getOutputStream();

            jasperToFileStream(OutFileType.DOCX, jasperReportPath, outParameter, dataSource, ouputStream);
            ouputStream.flush();
            ouputStream.close();

        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    /**
     * 显示html
     * @param response
     * @param request
     * @param jasperReportPath
     * @param outParameter
     * @param dataSource
     */
    public static void showHtml(HttpServletResponse response, HttpServletRequest request,
                                String jasperReportPath, Map outParameter, List dataSource) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");
            ServletOutputStream ouputStream = response.getOutputStream();
            jasperToFileStream(OutFileType.HTML, jasperReportPath, outParameter, dataSource, ouputStream);
            ouputStream.flush();
            ouputStream.close();
//            JasperPrint jasperPrint = getJasperPrint(jasperReportPath, outParameter, dataSource);
//            JRAbstractExporter exporter = getJRExporter(OutFileType.HTML);
//            //ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//
//            //导出文件到指定流
//            exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
//            SimpleHtmlExporterConfiguration configuration = new SimpleHtmlExporterConfiguration();
//            exporter.setConfiguration(configuration);
//
//            exporter.exportReport();


        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    /**
     * 下载文件
     * @param response
     * @param request
     * @param jasperReportPath 二进制报表位置
     * @param outParameter 外部参数
     * @param dataSource  数据源
     * @param defaultFilename 文件名称，不带扩展名
     * @param fileType 文件类型
     */
    public static void exportFile(HttpServletResponse response, HttpServletRequest request,
                                  String jasperReportPath, Map outParameter, List dataSource, String defaultFilename, OutFileType fileType) {
        try {
            //response.setContentType("application/vnd_ms-excel;charset=utf-8");
            String contentType = getContentType(fileType);
            //设置导出文件格式
            response.setContentType(contentType + ";charset=utf-8");
            String defaultname = getFileName(fileType, defaultFilename);
            //String fileName = new String(defaultname.getBytes("GBK"), "ISO8859_1");//可以正常输出
            String fileName = URLEncoder.encode(defaultname, "UTF-8");
            //设置导出文件名称
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            ServletOutputStream ouputStream = response.getOutputStream();
            jasperToFileStream(fileType, jasperReportPath, outParameter, dataSource, ouputStream);
            ouputStream.flush();
            ouputStream.close();

        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex);
        }

    }

    private static void exportDocx(HttpServletResponse response, HttpServletRequest request,
                                   String jasperReportPath, Map outParameter, List dataSource, String defaultFilename) {
        try {
            //response.setContentType("application/vnd_ms-excel;charset=utf-8");
            //设置导出文件格式
            response.setContentType("application/msword;charset=utf-8");
            String defaultname = getFileName(OutFileType.PDF, defaultFilename);
            String fileName = new String(defaultname.getBytes("GBK"), "ISO8859_1");
            //设置导出文件名称
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            ServletOutputStream ouputStream = response.getOutputStream();
            jasperToFileStream(OutFileType.DOCX, jasperReportPath, outParameter, dataSource, ouputStream);
            ouputStream.flush();
            ouputStream.close();

        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    private static void exportXlsx(HttpServletResponse response, HttpServletRequest request, String jasperReportPath, Map outParameter, List dataSource, String defaultFilename) {
        try {
            //设置导出文件格式
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            String defaultname = getFileName(OutFileType.XLS, defaultFilename);
            String fileName = new String(defaultname.getBytes("GBK"), "ISO8859_1");
            //设置导出文件名称
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            ServletOutputStream ouputStream = response.getOutputStream();
            jasperToFileStream(OutFileType.XLSX, jasperReportPath, outParameter, dataSource, ouputStream);
            ouputStream.flush();
            ouputStream.close();

        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    private static void exportPdf(HttpServletResponse response, HttpServletRequest request, String jasperReportPath, Map outParameter, List dataSource, String defaultFilename) {

        try {
            response.setContentType("application/pdf");
            String defaultname = getFileName(OutFileType.PDF, defaultFilename);

            String fileName = new String(defaultname.getBytes("GBK"), "ISO8859_1");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);

            ServletOutputStream ouputStream = response.getOutputStream();
            jasperToFileStream(OutFileType.PDF, jasperReportPath, outParameter, dataSource, ouputStream);
            ouputStream.flush();
            ouputStream.close();

        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    /**
     * 获取设置输出的文件名称
     * @param outFileType
     * @param defaultFilename
     * @return
     */
    static String getFileName(OutFileType outFileType, String defaultFilename) {
        String defaultname = null;
        if (defaultFilename != null && defaultFilename.trim() != "") {
            defaultname = defaultFilename;
        } else {
            SimpleDateFormat format0 = new SimpleDateFormat("yyyyMMddHHmmss");
            //这个就是把时间戳经过处理得到期望格式的时间
            defaultname = format0.format(System.currentTimeMillis());
        }
        defaultname = defaultname + "." + outFileType.name();

        return defaultname;
    }


    private static byte[] jasperToPdf(String jasperReportPath, Map outParameter, List dataSource) {
        byte[] bytes = null;

        try {
            JasperPrint jasperPrint = getJasperPrint(jasperReportPath, outParameter, dataSource);
            //5.通过JasperExportManager管理nb工具进行报表输出文档，生成report4.pdf文件
            bytes = JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex);
        }

        return bytes;
    }

    private static byte[] jasperToDocx(String jasperReportPath, HashMap<String, Object> outParameter, List dataSource) {
        byte[] bytes = null;

        try {
            JasperPrint jasperPrint = getJasperPrint(jasperReportPath, outParameter, dataSource);
            JRDocxExporter exporter = new JRDocxExporter();
            //JRRtfExporter exporter = new JRRtfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//            File exportReportFile = new File(fileName + ".docx");
//            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportReportFile));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
            exporter.exportReport();
            bytes = baos.toByteArray();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
        return bytes;
    }
    /**
     * OutputStream转字节
     *
     * @param outputStream
     * @return
     */
    private static byte[] outPutStream2Byte(OutputStream outputStream) {
        ByteArrayOutputStream baos;
        baos = (ByteArrayOutputStream) outputStream;
        return baos.toByteArray();
    }

    /**
     * 字节转OutputStream
     *
     * @param bytes
     * @return
     */
    public static OutputStream byte2OutPutStream(byte[] bytes) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int ch;
        while ((ch = inputStream.read()) != -1) {
            outputStream.write(ch);
        }
        return outputStream;
    }

    public static void main(String[] args) {
        // TODO 自行测试
    }
}

