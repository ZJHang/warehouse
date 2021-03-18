package com.zjhang.service;

import com.alibaba.fastjson.JSON;
import com.zjhang.pojo.TicketVo;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserSreviceImpl
 * @Description TODO
 * @Author zhengjh
 * @Date 2021/3/17 17:40
 * @Version 1.0
 **/
@Service // 启动交给spring托管（这里的@Service注解是spring下的，切记！！！）
public class UserSreviceImpl implements UserService{
    @Reference // 远程引用注解  1、pom坐标 2、定义相同路径下（全类名一致）的相同接口名称   这里为了测试使用2，真实开发中使用1，引入pom
    private TicketService ticketService;


    @Override
    public String buyTicket() {
        TicketVo ticket = ticketService.getTicket();

        return "您已成功买票，其信息为：" + JSON.toJSONString(ticket);
    }
}
