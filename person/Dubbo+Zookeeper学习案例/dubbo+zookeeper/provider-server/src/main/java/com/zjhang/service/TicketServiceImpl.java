package com.zjhang.service;

import com.zjhang.pojo.TicketVo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @ClassName TicketServiceImpl
 * @Description TODO
 * @Author zhengjh
 * @Date 2021/3/17 16:42
 * @Version 1.0
 **/
@Service // 可以被zookeeper注册中心扫描到，在项目一启动就自动注册到zookeeper注册中心（注意：这个@Service注解是dubbo下的，切记！！！！）
@Component // 这里使用了dubbo后尽量不要用@Service注解
public class TicketServiceImpl implements TicketService {
    @Override
    public TicketVo getTicket() {
        // 模拟买票操作，这里生产者组装票信息
        // .........
        TicketVo ticketVo = TicketVo.builder()
                .name("《阿凡达》")
                .price(new BigDecimal("66.66"))
                .number(10)
                .build();
        return ticketVo;
    }
}
