package com.gisquest.ireport.service.impl;

import com.gisquest.ireport.conf.ReportsProperties;
import com.gisquest.ireport.domain.*;
import com.gisquest.ireport.service.MakePdfService;
import com.gisquest.ireport.utils.JasperReportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName MakePdfServiceImpl
 * @Description 生成pdf实现层
 * @Author zhengjh
 * @Date 2021/3/1 10:07
 * @Version 1.0
 **/
@Service
public class MakePdfServiceImpl implements MakePdfService {
    @Autowired
    ReportsProperties reportsProperties;

    @Override
    public void simpleComponentToPdf(HttpServletRequest request, HttpServletResponse response) {
        String xtPath = reportsProperties.getBasePath() + "/";
        String jrPath = xtPath + "xxx.jasper";

        JasperReportUtil.showPdf(response, request, jrPath, null, null, reportsProperties.getEnableAddWater(), reportsProperties.getWaterContent());
    }

    @Override
    public void complexComponentToPdf(HttpServletRequest request, HttpServletResponse response) {
        String xtPath = reportsProperties.getBasePath() + "/";
        String jrPath = xtPath + "test.jasper";

        List<StudentEntity> studentEntityList1 = new ArrayList<>(3);
        StudentEntity s1 = StudentEntity.builder().name("小明").age("15").sex("男").build();
        StudentEntity s2 = StudentEntity.builder().name("小红").age("16").sex("女").build();
        StudentEntity s3 = StudentEntity.builder().name("小李").age("18").sex("男").build();
        studentEntityList1.add(s1);
        studentEntityList1.add(s2);
        studentEntityList1.add(s3);

        List<ClassEntity> classEntityList = new ArrayList<>(1);
        ClassEntity c1 = ClassEntity.builder().className("初三（1）班").studentList(studentEntityList1).build();
        classEntityList.add(c1);

        JasperReportUtil.showPdf(response, request, jrPath, null, classEntityList, reportsProperties.getEnableAddWater(), reportsProperties.getWaterContent());
    }

    @Override
    public void makeSqsPdf(HttpServletRequest request, HttpServletResponse response) {
        String xtPath = reportsProperties.getBasePath() + "/";
        String jrPath = xtPath + "Info_ApplyForm_export.jasper";

        HashMap<String, Object> paramap = new HashMap<>(7);
        paramap.put("SUBREPORT_DIR", xtPath);
        paramap.put("DJLX", "100");
        paramap.put("QLLX", "1");
        paramap.put("YWH", "W330822-20210301-000001");
        paramap.put("SLSJ", "2021-03-01");
        paramap.put("SLR", "张三");
        paramap.put("ZLLB", "国有建设用地使用权-首次登记");

        JasperReportUtil.showPdf(response, request, jrPath, paramap, getSqsInfoList(), reportsProperties.getEnableAddWater(), reportsProperties.getWaterContent());
    }

    /**
     * 获取生成申请书pdf数据列表
     *
     * @return
     */
    public List<SqsEntity> getSqsInfoList() {
        List<SqrEntity> sqrEntityList = new ArrayList<>();
        SqrEntity sqrEntity = SqrEntity.builder()
                .qlrmc("张三").gyqk("单独所有").qlrzjlx("1")
                .qlrzjh("330822202103010001").qlrtxdz("浙江杭州").qlrdh("17812345678")
                .ywrmc("李四").ywrzjlx("1").ywrzjh("330822202103010002")
                .ywrdh("17887654321").build();
        sqrEntityList.add(sqrEntity);

        List<BdcEntity> bdcEntityList = new ArrayList<>();
        BdcEntity bdcEntity = BdcEntity.builder()
                .zl("杭州市西湖小区1幢1104室").bdcdyh("3332222111111")
                .bdclxfw("1").bdclxgzw("1").mj("111.22㎡").yt("养殖")
                .ybdcqzh("浙xxxxxx").sqly("测试").dyfs("1")
                .dyje("123万元").qlqssj("2020年01月01日").qljssj("2090年12月31日")
                .sqfbcz("1").bz("测试测试").build();
        bdcEntityList.add(bdcEntity);

        List<SqsEntity> sqsEntityList = new ArrayList<>();
        SqsEntity sqsEntity = SqsEntity.builder()
                .sqrxx(sqrEntityList).bdcxx(bdcEntityList).build();
        sqsEntityList.add(sqsEntity);

        return sqsEntityList;
    }
}
