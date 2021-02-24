package direct.topics;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitUtils;

import java.io.IOException;

/**
 * @Description Topics 模型生产者
 * @Author qi
 * @Date 2021/2/24 13:48
 * @ClassName TopicsProvider
 **/
public class TopicsProvider {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        // 声明交换机即交换机类型
        channel.exchangeDeclare("logs_topics", "topic");

        // routingKey
        String routingKey = "log.user.save";
        String routingKey2 = "log.admin.delete";

        // 发布消息
        channel.basicPublish("logs_topics", routingKey, null, ("这里是 topics 动态路由模型，routingKey [" + routingKey + "]").getBytes());
        channel.basicPublish("logs_topics", routingKey2, null, ("这里是 topics 动态路由模型，routingKey [" + routingKey2 + "]").getBytes());

        RabbitUtils.closeConnectionAndChannel(channel, connection);
    }
}