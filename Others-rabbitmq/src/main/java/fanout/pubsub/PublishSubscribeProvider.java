package fanout.pubsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitUtils;

import java.io.IOException;

/**
 * @Description 广播模式生产者
 * @Author qi
 * @Date 2021/2/23 22:49
 * @ClassName PublishSubscribeProvider
 **/
public class PublishSubscribeProvider {

    public static void main(String[] args) throws IOException {
        // 获取连接对象
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        /*
         * 将通道声明指定交换机
         * 参数1：交换机的名称
         * 参数2：交换机类型，使用广播类型，fanout
         */
        channel.exchangeDeclare("logs", "fanout");

        /*
         * 发送消息
         * 参数1：exchange：交换机名称
         * 参数2：routingKey：在该广播模式下，该参数没有意义，可以空着
         * 参数3：props：是否持久化
         * 参数4：要发送的消息
         */
        channel.basicPublish("logs", "", null, "fanout type message".getBytes());

        // 关闭连接
        RabbitUtils.closeConnectionAndChannel(channel, connection);
    }
}