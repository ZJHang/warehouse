package com.zjhang.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName TicketVo
 * @Description TODO
 * @Author zhengjh
 * @Date 2021/3/17 16:44
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketVo implements Serializable {
    private static final Long serializableID = 1L;

    /**
     * 电影名称
     */
    private String name;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 数量
     */
    private Integer number;
}
