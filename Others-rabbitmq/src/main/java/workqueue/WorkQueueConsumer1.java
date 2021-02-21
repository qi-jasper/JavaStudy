package workqueue;

import com.rabbitmq.client.*;
import utils.RabbitUtils;

import java.io.IOException;

/**
 * @Description Work Queues 模型消费者 1
 * @Author qi
 * @Date 2020/8/19 15:17
 * @ClassName WorkQueueConsumer1
 **/
public class WorkQueueConsumer1 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare("work", true, false, false, null);

        // 每次只能消费一个消息，将要消费的 1 条消息通过通道从队列中传递给消费者，剩余的消息放在队列中等待消费
        channel.basicQos(1);
        // 参数2：autoAck：消息自动确认，消费者自动向 RabbitMQ 确认消息消费
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-1：" + new String(body));
                // 手动确认机制，参数1: 确认队列中哪个具体的消息；参数2: 是否开启多个消息同时确认
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}