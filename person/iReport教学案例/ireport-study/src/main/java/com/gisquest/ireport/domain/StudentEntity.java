package com.gisquest.ireport.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName StudentEntity
 * @Description 学生信息实体类
 * @Author zhengjh
 * @Date 2021/3/5 15:27
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentEntity {
    private String name;

    private String age;

    private String sex;
}
