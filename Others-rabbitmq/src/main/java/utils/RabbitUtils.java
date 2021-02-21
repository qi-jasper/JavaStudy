package utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Description RabbitMQ 连接工具类
 * @Author qi
 * @Date 2020/8/16 16:54
 * @ClassName RabbitUtils
 **/
public class RabbitUtils {

    private static final ConnectionFactory CONNECTION_FACTORY;

    static {
        // 创建新的连接对象
        CONNECTION_FACTORY = new ConnectionFactory();
        // 连接 IP
        CONNECTION_FACTORY.setHost("192.168.6.7");
        // 连接端口
        CONNECTION_FACTORY.setPort(5672);
        // 连接到的虚拟主机
        CONNECTION_FACTORY.setVirtualHost("test-qi");
        // 用户名
        CONNECTION_FACTORY.setUsername("qi");
        // 密码
        CONNECTION_FACTORY.setPassword("qi");
    }

    /**
     * 定义提供连接对象的方法
     * @return 连接对象
     */
    public static Connection getConnection() {
        try {
            return CONNECTION_FACTORY.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭通道和关闭连接工具的方法
     * @param channel 通道
     * @param connection 连接对象
     */
    public static void closeConnectionAndChannel(Channel channel, Connection connection) {
        try {
            if (channel != null) {
                channel.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}