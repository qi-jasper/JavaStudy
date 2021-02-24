package direct.routing;

import com.rabbitmq.client.*;
import utils.RabbitUtils;

import java.io.IOException;

/**
 * @Description Routing 模型的消费者-1
 * @Author qi
 * @Date 2021/2/24 11:19
 * @ClassName RoutingConsumer1
 **/
public class RoutingConsumer1 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        // 声明交换机
        channel.exchangeDeclare("logs_direct", "direct");

        // 创建一个临时队列
        String tempQueue = channel.queueDeclare().getQueue();

        // 基于 routingKey 绑定队列和交换机，可以绑定多个
        channel.queueBind(tempQueue, "logs_direct", "error");

        // 消费消息
        channel.basicConsume(tempQueue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("Consumer-1: " + new String(body));
            }
        });
    }
}