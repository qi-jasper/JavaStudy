package workqueue;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @Description
 * @Author qi
 * @Date 2020/8/19 15:17
 * @ClassName WorkQueueConsumer1
 **/

public class WorkQueueConsumer1 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare("work", true, false, false, null);

        channel.basicConsume("work", true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-1：" + new String(body));
                // 参数1: 确认队列中哪个具体的消息；参数2: 是否开启多个消息同时确认　
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
