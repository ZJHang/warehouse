package com.gisquest.ireport.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName SqsEntity
 * @Description 申请书信息实体类
 * @Author zhengjh
 * @Date 2021/3/1 10:40
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SqsEntity {
    private List<SqrEntity> sqrxx;

    private List<BdcEntity> bdcxx;
}
