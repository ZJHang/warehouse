package com.gisquest.ireport.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName BdcEntity
 * @Description 不动产信息实体类
 * @Author zhengjh
 * @Date 2021/3/1 10:37
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BdcEntity {
    private String zl;
    private String mj;
    private String bdcdyh;
    private String yt;
    private String ybdcqzh;
    private String sqly;
    private String dyje;
    private String qlqssj;
    private String qljssj;
    private String zjjzwdyfw;
    private String xydbdcqzh;
    private String lymd;
    private String dyiqqssj;
    private String dyiqjssj;
    private String bz;
    private String bdclxtd;
    private String bdclxfw;
    private String bdclxgzw;
    private String bdclxsl;
    private String bdclxhy;
    private String dyfs;
    private String sqfbcz;
}
