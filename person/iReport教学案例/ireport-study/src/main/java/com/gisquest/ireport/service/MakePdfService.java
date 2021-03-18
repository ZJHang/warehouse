package com.gisquest.ireport.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName MakePdfService
 * @Description 生成pdf接口层
 * @Author zhengjh
 * @Date 2021/3/1 10:06
 * @Version 1.0
 **/
public interface MakePdfService {
    void simpleComponentToPdf(HttpServletRequest request, HttpServletResponse response);

    void complexComponentToPdf(HttpServletRequest request, HttpServletResponse response);

    void makeSqsPdf(HttpServletRequest request, HttpServletResponse response);
}
