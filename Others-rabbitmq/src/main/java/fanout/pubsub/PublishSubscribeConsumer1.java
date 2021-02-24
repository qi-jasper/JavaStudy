package fanout.pubsub;

import com.rabbitmq.client.*;
import utils.RabbitUtils;

import java.io.IOException;

/**
 * @Description 广播模式消费者-1
 * @Author qi
 * @Date 2021/2/23 23:55
 * @ClassName PublishSubscribeConsumer1
 **/
public class PublishSubscribeConsumer1 {

    public static void main(String[] args) throws IOException {
        // 获取连接对象
        Connection connection = RabbitUtils.getConnection();

        // 通过连接对象获取通道
        Channel channel = connection.createChannel();

        // 通道绑定交换机：channel.exchangeBind()，也可以像生产者那样声明一个交换机：channel.exchangeDeclare()
        channel.exchangeDeclare("logs", "fanout");

        // 通过通道创建一个临时队列
        String tempQueue = channel.queueDeclare().getQueue();

        /*
         * 绑定交换机和队列，通过通道绑定
         * 参数1：queue：队列名称
         * 参数2：exchange：交换机名称
         * 参数3：routingKey，在广播模式下没有作用，置空
         */
        channel.queueBind(tempQueue, "logs", "");

        // 消费消息
        channel.basicConsume(tempQueue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body)
                    throws IOException {
                System.out.println("Consumer-1：" + new String(body));
            }
        });
    }
}