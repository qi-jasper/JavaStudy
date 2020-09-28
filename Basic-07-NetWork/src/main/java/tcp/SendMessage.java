package tcp;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Description
 * @Author qi
 * @Date 2020/5/1 16:45
 * @ClassName TCPTest
 **/

/**
 * TCP协议采用“三次握手“方式建立连接，点对点通信，提供可靠传输
 * UDP协议不需要建立连接，每个数据包大小限制在64K内，不论接收方是否准备好，也不需要接收方确认，是不可靠传输。
 *      可以广播发送，发送结束无需释放连接，开销小，速度快
 */
public class SendMessage {

    // 客户端
    @Test
    public void client() throws IOException {
        // 1.创建socket对象，指明服务端的IP和端口号
        InetAddress inet = InetAddress.getByName("127.0.0.1");
        // 传入客户端IP和指定一个端口号
        Socket socket = new Socket(inet, 8888);

        // 2.获取一个输出流，用于输出数据
        OutputStream os = socket.getOutputStream();
        // 写数据
        os.write("这里是客户端".getBytes());

        // 3.资源关闭
        os.close();
        socket.close();
    }

    // 服务端
    @Test
    public void server() throws IOException {

        // 1.创建服务器端的ServerSocket，指明自己的端口号
        ServerSocket ss = new ServerSocket(8888);
        // 2.使用accept()接受来自客户端的数据
        Socket accept = ss.accept();
        // 3.获取输入流
        InputStream is = accept.getInputStream();

        // 不建议这样写，会有乱码
        /*byte[] buffer = new byte[20];
        int len;
        while ((len = is.read(buffer)) != -1) {
            String s = new String(buffer, 0, len);
            System.out.println(s);
        }*/
        // 4.读取输入流的数据
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        byte[] buffer = new byte[5];
        int len;
        while ((len = is.read(buffer)) != -1) {
            bs.write(buffer, 0, len);
        }

        System.out.println(bs.toString());

        // 5.关闭资源
        bs.close();
        is.close();
        accept.close();
        ss.close();

    }
}
