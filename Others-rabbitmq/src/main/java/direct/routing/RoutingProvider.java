package direct.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitUtils;

import java.io.IOException;

/**
 * @Description Routing 模型的生产者
 * @Author qi
 * @Date 2021/2/24 11:11
 * @ClassName RoutingProvider
 **/
public class RoutingProvider {

    public static void main(String[] args) throws IOException {
        // 获取连接对象
        Connection connection = RabbitUtils.getConnection();

        // 获取连接通道对象
        Channel channel = connection.createChannel();

        /*
         * 通过通道声明交换机
         * 参数1：exchange：交换机名称
         * 参数2：type：交换机类型
         */
        channel.exchangeDeclare("logs_direct", "direct");

        // 定义 infoRoutingKey
        String infoRoutingKey = "info";
        // 定义 waringRoutingKey
        String warningRoutingKey = "warning";
        // 定义 errorRoutingKey
        String errorRoutingKey = "error";
        // 定义 debugRoutingKey
        String debugRoutingKey = "debug";

        /*
         * 发送消息
         * 参数1：exchange：交换机名称
         * 参数2：infoRoutingKey：infoRoutingKey
         * 参数3：props
         * 参数4：要发送的消息
         */
        channel.basicPublish("logs_direct", infoRoutingKey, null, ("这是 direct 类型交换机基于 [" + infoRoutingKey + "] 的 routingKey 发送的消息").getBytes());
        channel.basicPublish("logs_direct", warningRoutingKey, null, ("这是 direct 类型交换机基于 [" + warningRoutingKey + "] 的 routingKey 发送的消息").getBytes());
        channel.basicPublish("logs_direct", errorRoutingKey, null, ("这是 direct 类型交换机基于 [" + errorRoutingKey + "] 的 routingKey 发送的消息").getBytes());
        channel.basicPublish("logs_direct", debugRoutingKey, null, ("这是 direct 类型交换机基于 [" + debugRoutingKey + "] 的 routingKey 发送的消息").getBytes());

        // 关闭连接
        RabbitUtils.closeConnectionAndChannel(channel, connection);
    }
}