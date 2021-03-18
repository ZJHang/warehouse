package com.gisquest.ireport.controller;

import com.gisquest.ireport.service.MakePdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName MakePdfController
 * @Description 生成PDF接口服务层
 * @Author zhengjh
 * @Date 2021/3/1 10:04
 * @Version 1.0
 **/
@RestController
public class MakePdfController {
    @Autowired
    MakePdfService makePdfService;

    /**
     * 测试
     *
     * @return
     */
    @GetMapping("/ireport/study/test")
    public String test() {
        return "Welcome to ireport study !!!";
    }

    /**
     * 简单组件生产PDF
     *
     * @return
     */
    @GetMapping("/ireport/study/simpleComponentToPdf")
    public void simpleComponentToPdf(HttpServletRequest request, HttpServletResponse response) {
        makePdfService.simpleComponentToPdf(request, response);
    }

    /**
     * 复杂组件生产PDF
     *
     * @return
     */
    @GetMapping("/ireport/study/complexComponentToPdf")
    public void complexComponentToPdf(HttpServletRequest request, HttpServletResponse response) {
        makePdfService.complexComponentToPdf(request, response);
    }

    /**
     * 生成申请表单pdf
     *
     * @param request
     * @param response
     */
    @GetMapping("/ireport/study/makeSqsPdf")
    public void makeSqsPdf(HttpServletRequest request, HttpServletResponse response) {
        makePdfService.makeSqsPdf(request, response);
    }
}
