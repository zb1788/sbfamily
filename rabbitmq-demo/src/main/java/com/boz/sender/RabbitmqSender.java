package com.boz.sender;

import com.boz.bean.Order;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author boz
 * @date 2019/8/28
 */
@Component
public class RabbitmqSender {
    @Autowired
    RabbitTemplate rabbitTemplate;

    //回调函数: confirm确认
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.err.println("correlationData: " + correlationData);
            //获取订单id
            String messageId = correlationData.getId();
            if(ack){
                //如果confirm返回成功 则进行更新 更改订单日志的状态

            } else {
                //失败则进行具体的后续操作:重试 或者补偿等手段 更改订单日志表的重试次数
                System.err.println("异常处理...");
            }
        }
    };




    //发送订单消息
    public void send(Order order){

        //发送消息后会收到确认信息
        rabbitTemplate.setConfirmCallback(confirmCallback);
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(order.getMessageId());
        rabbitTemplate.convertAndSend("order-exchange",//exchange 交换机
                "order.abcd",// routingKey 路由
                order,//消息体内容
                correlationData);// 消息唯一ID
    }

}
