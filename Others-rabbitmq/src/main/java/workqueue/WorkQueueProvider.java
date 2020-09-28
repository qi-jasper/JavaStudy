package workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @Description
 * @Author qi
 * @Date 2020/8/19 15:09
 * @ClassName HelloProvider
 **/

public class WorkQueueProvider {

    public static void main(String[] args) throws IOException {
        // 1. 获取连接对象
        Connection connection = RabbitMQUtils.getConnection();

        // 2. 获取通道对象
        Channel channel = connection.createChannel();

        // 3. 通过通道声明队列
        channel.queueDeclare("work", true, false, false, null);

        // 4. 发布/生产 消息
        for (int i = 0; i < 100; i++) {
            channel.basicPublish("", "work", null, ("第" + i + "条hello work queue").getBytes());
        }

        // 5. 关闭资源
        RabbitMQUtils.closeConnectionAndChannel(channel, connection);
    }
}
