package com.gisquest.ireport.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName ReportsProperties
 * @Description 报表配置信息
 * @Author zhengjh
 * @Date 2021/3/1 10:22
 * @Version 1.0
 **/
@Configuration
@ConfigurationProperties(prefix = "gisq.microreports")
@Data
public class ReportsProperties {
    /**
     * 报表路径
     */
    String basePath;

    /**
     * 是否添加水印
     */
    Boolean enableAddWater;

    /**
     * 水印内容
     */
    String waterContent;
}
