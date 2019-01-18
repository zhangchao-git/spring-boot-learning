package cn.zoua.allinone.service;

import cn.zoua.allinone.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * rabbitmq demo
 */
@Service
public class BookService {

    @RabbitListener(queues = "zoua.queue.direct")
    public void receive(Book book) {
        System.out.println("收到消息：" + book);
    }

    @RabbitListener(queues = "zoua.queue.fanout01")
    public void receive02(Message message) {
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
