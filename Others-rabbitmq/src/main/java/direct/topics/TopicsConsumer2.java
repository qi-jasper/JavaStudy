package direct.topics;

import com.rabbitmq.client.*;
import utils.RabbitUtils;

import java.io.IOException;

/**
 * @Description Topics 模型消费者-2
 * @Author qi
 * @Date 2021/2/24 13:56
 * @ClassName TopicsConsumer2
 **/
public class TopicsConsumer2 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        // 声明交换机
        channel.exchangeDeclare("logs_topics", "topic");
        // 创建临时队列
        String queue = channel.queueDeclare().getQueue();
        // 根据 routingKey 绑定交换机和队列
        channel.queueBind(queue, "logs_topics", "log.#");

        // 消费消息
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-2：" + new String(body));
            }
        });
    }
}