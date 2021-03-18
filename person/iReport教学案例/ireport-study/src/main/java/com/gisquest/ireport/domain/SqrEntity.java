package com.gisquest.ireport.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName SqrEntity
 * @Description 申请人信息实体类
 * @Author zhengjh
 * @Date 2021/3/1 10:36
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SqrEntity {
    private String qlrmc;
    private String gyqk;
    private String qlrzjlx;
    private String qlrzjh;
    private String qlrtxdz;
    private String qlrdh;
    private String qlrfrmc;
    private String qlrfrdh;
    private String qlrdlrmc;
    private String qlrdlrdh;
    private String qlrdljg;
    private String ywrmc;
    private String ywrzjlx;
    private String ywrzjh;
    private String ywrtxdz;
    private String ywrdh;
    private String ywrfrmc;
    private String ywrfrdh;
    private String ywrdlrmc;
    private String ywrdlrdh;
    private String ywrdljg;
}
