package com.gisquest.ireport.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName ClassEntity
 * @Description 班级信息实体类
 * @Author zhengjh
 * @Date 2021/3/5 15:31
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassEntity {
    private String className;

    private List<StudentEntity> studentList;
}
