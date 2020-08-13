package com.boz.reciver;

import com.boz.bean.Order;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author boz
 * @date 2019/8/28
 */
@Component
public class RabbitmqReciver {
    //监听消息，如果有新消息交给RabbitHandler处理
    //会自动在rabbitmq中创建对应的交换机，队列，以及绑定关系和routingkey
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "order-queue", durable = "true"),
            exchange = @Exchange(value = "order-exchange",durable = "true",type = "topic"),
            key = "order-*"
    ))




    @RabbitHandler
    public void onOrderMessage(@Payload Order order, @Headers Map<String,Object> headers, Channel channel) throws IOException {
        System.out.println("-----------收到订单---------------");
        System.out.println("订单ID：" + order.getId());

        Long deliverTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);


        //手动确认模式
        channel.basicAck(deliverTag,false);
    }

}
