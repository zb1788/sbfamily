package com.boz;

import com.boz.bean.Order;
import com.boz.sender.RabbitmqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author boz
 * @date 2019/8/28
 */
@RestController
public class RabbitmqController {

    @Autowired
    RabbitmqSender rabbitmqSender;

    @GetMapping("send")
    public String send(){

        Order order = new Order();
        order.setId("2019030400000001");
        order.setName("测试订单1");
        order.setMessageId(System.currentTimeMillis()+"$"+ UUID.randomUUID().toString());
        System.out.println(order);
        rabbitmqSender.send(order);
        return "OK";
    }
}
