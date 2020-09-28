package hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @Description
 * @Author qi
 * @Date 2020/8/16 17:03
 * @ClassName HelloProvider
 **/

public class HelloProvider {

    public static void main(String[] args) throws IOException {
        HelloProvider helloProvider = new HelloProvider();
        helloProvider.sendMessage();
    }

    public void sendMessage() throws IOException {

        // 获取连接对象
        Connection connection = RabbitMQUtils.getConnection();

        // 获取连接中通道
        Channel channel = connection.createChannel();

        /**
         * 绑定对应队列
         * 参数1: 队列名称，如果不存在则自动创建
         * 参数2: 用来定义队列特性，是否要持久化, true: 持久化队列， false: 不持久化队列
         * 参数3: exclusive: 是否独占队列, true: 独占队列， false：不独占队列
         * 参数4: autuDelete: 是否在消费完成后删除队列， true: 自动删除  false: 不自动删除
         * 参数5: 额外附加参数
         */
        channel.queueDeclare("hello", false, false, false, null);

        /**
         * 参数1: 交换机名称
         * 参数2: 队列名称
         * 参数3: 传递消息额外设置
         * 参数4: 消息的具体内容
         */

        final String MESSAGE ="Hello! RabbitMQ!";

        channel.basicPublish("", "hello", null, MESSAGE.getBytes());

        // 关闭连接
        RabbitMQUtils.closeConnectionAndChannel(channel, connection);
    }
}
