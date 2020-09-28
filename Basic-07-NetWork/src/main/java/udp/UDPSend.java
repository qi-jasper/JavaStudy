package udp;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @Description UDP编程
 * @Author qi
 * @Date 2020/5/1 17:38
 * @ClassName UDPSend
 **/

public class UDPSend {

    @Test
    public void sender() throws IOException {

        DatagramSocket socket = new DatagramSocket();

        // 模拟封装的数据报
        String str = "UDP发送的数据";
        byte[] data = str.getBytes();
        InetAddress inet = InetAddress.getLocalHost();

        DatagramPacket packet = new DatagramPacket(data, 0, data.length, inet, 8899);

        socket.send(packet);
        socket.close();
    }

    @Test
    public void reciver() throws IOException {

        DatagramSocket socket = new DatagramSocket(8899);

        byte[] buffer = new byte[100];
        DatagramPacket pack = new DatagramPacket(buffer, 0, buffer.length);

        socket.receive(pack);

        System.out.println(new String(pack.getData(), 0, pack.getLength()));

        socket.close();
    }
}
