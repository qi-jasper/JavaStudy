package workqueue;

import com.rabbitmq.client.*;
import utils.RabbitUtils;

import java.io.IOException;

/**
 * @Description Work Queues 模型消费者 2
 * @Author qi
 * @Date 2020/8/19 15:20
 * @ClassName WorkQueueConsumer2
 **/
public class WorkQueueConsumer2 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare("work", true, false, false, null);

        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-2：" + new String(body));
                // 手动确认机制，参数1: 确认队列中哪个具体的消息；参数2: 是否开启多个消息同时确认
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}