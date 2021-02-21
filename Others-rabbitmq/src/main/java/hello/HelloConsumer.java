package hello;

import com.rabbitmq.client.*;
import utils.RabbitUtils;

import java.io.IOException;

/**
 * @Description Hello World 模型消费者
 * @Author qi
 * @Date 2020/8/16 17:19
 * @ClassName HelloConsumer
 **/
public class HelloConsumer {

    public static void main(String[] args) throws IOException {

        // 获取连接对象
        Connection connection = RabbitUtils.getConnection();

        // 获取连接中通道
        Channel channel = connection.createChannel();

        // 通道绑定对象，和生产者那里的参数需保持一致
        channel.queueDeclare("hello", false, false, false, null);

        /*
         * 消费消息
         * 参数1: 消费哪个队列的消息，即队列名称
         * 参数2: 是否开启消息的自动确认机制
         * 参数3: 消费时的回调窗口
         */
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });
    }
}