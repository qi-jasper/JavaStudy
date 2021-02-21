package hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitUtils;

import java.io.IOException;

/**
 * @Description Hello World 模型生产者
 * @Author qi
 * @Date 2020/8/16 17:03
 * @ClassName HelloProvider
 **/
public class HelloProvider {

    public static void main(String[] args) throws IOException {
        HelloProvider helloProvider = new HelloProvider();
        helloProvider.sendMessage();
    }

    /**
     * 发送消息
     * @throws IOException IO 异常
     */
    public void sendMessage() throws IOException {

        // 定义要发送的消息
        final String message ="Hello! RabbitMQ!";

        // 获取连接对象
        Connection connection = RabbitUtils.getConnection();

        // assert connection != null;

        // 获取连接中通道，连接通过通道去发送消息，通道要与消息队列绑定
        Channel channel = connection.createChannel();

        /*
         * 绑定对应队列
         * 参数1: 队列名称，如果不存在则自动创建
         * 参数2: 用来定义队列特性，是否要持久化, true: 持久化队列， false: 不持久化队列
         * 参数3: exclusive: 是否独占队列, true: 独占队列， false：不独占队列
         * 参数4: autuDelete: 是否在消费完成后删除队列， true: 自动删除  false: 不自动删除
         * 参数5: 额外附加参数
         */
        channel.queueDeclare("hello", false, false, false, null);

        /*
         * 发送消息到队列中
         * 参数1: 交换机名称（因为这种模式不需要用到交换机，所以空着就行）
         * 参数2: 要把消息发送到的队列的名称
         * 参数3: 传递消息额外设置
         * 参数4: 消息的具体内容
         */
        channel.basicPublish("", "hello", null, message.getBytes());

        // 关闭连接
        RabbitUtils.closeConnectionAndChannel(channel, connection);
    }
}