package ip;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Description
 * @Author qi
 * @Date 2020/5/1 16:13
 * @ClassName InetAddressTest
 **/

public class InetAddressTest {

    /**
     * IP地址常用方法
     * IP地址和端口号组合得出一个网络套接字：Socket
     * 要求，不同的进程有不同的端口号：0~65535
     */
    @Test
    public void ipAddressTest() throws UnknownHostException {

        InetAddress byIP = InetAddress.getByName("112.80.248.75");
        System.out.println(byIP);

        InetAddress byName = InetAddress.getByName("www.baidu.com");
        System.out.println(byName);

        // 获取本机
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);

    }

}
