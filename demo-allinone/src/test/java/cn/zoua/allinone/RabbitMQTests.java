package cn.zoua.allinone;

import cn.zoua.allinone.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQTests {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	AmqpAdmin amqpAdmin;

	@Test
	public void createExchange(){
//		//创建交换器
		amqpAdmin.declareExchange(new DirectExchange("zoua.exchange.direct"));
		//创建队列
		amqpAdmin.declareQueue(new Queue("zoua.queue.direct",true));
        //创建绑定规则
		amqpAdmin.declareBinding(new Binding("zoua.queue.direct", Binding.DestinationType.QUEUE,"zoua.exchange.direct","zoua.direct",null));


		//创建交换器
		amqpAdmin.declareExchange(new FanoutExchange("zoua.exchange.fanout"));
		//创建队列
		amqpAdmin.declareQueue(new Queue("zoua.queue.fanout01",true));
		//创建绑定规则
		amqpAdmin.declareBinding(new Binding("zoua.queue.fanout01", Binding.DestinationType.QUEUE,"zoua.exchange.fanout","zoua.fanout01",null));
		//创建队列
		amqpAdmin.declareQueue(new Queue("zoua.queue.fanout02",true));
		//创建绑定规则
		amqpAdmin.declareBinding(new Binding("zoua.queue.fanout02", Binding.DestinationType.QUEUE,"zoua.exchange.fanout","zoua.fanout02",null));
	}

	/**
	 * 1、单播（点对点）
	 */
	@Test
	public void contextLoads() {
		//Message需要自己构造一个;定义消息体内容和消息头
		//rabbitTemplate.send(exchage,routeKey,message);

		//object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq；
		//rabbitTemplate.convertAndSend(exchage,routeKey,object);
		//对象被默认序列化以后发送出去
		rabbitTemplate.convertAndSend("zoua.exchange.direct","zoua.direct",new Book("西游记","吴承恩"));

	}

	//接受数据,如何将数据自动的转为json发送出去
	@Test
	public void receive(){
		Object o = rabbitTemplate.receiveAndConvert("zoua.queue.fanout01");
		System.out.println(o.getClass());
		System.out.println(o);
	}

	/**
	 * 广播
	 */
	@Test
	public void sendMsg(){
		rabbitTemplate.convertAndSend("zoua.exchange.fanout","",new Book("红楼梦","曹雪芹"));
	}

}
