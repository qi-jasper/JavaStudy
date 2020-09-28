package tcp;

/**
 * @Description
 * @Author qi
 * @Date 2020/5/1 17:02
 * @ClassName SendFiles
 **/

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * 客户端发送文件到服务端，服务端将文件保存到本地
 */
public class SendFiles {


    @Test
    public void client() throws IOException {
        // 1.创建socket
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 8888);
        // 2.获取输出流
        OutputStream os = socket.getOutputStream();
        // 3.文件
        FileInputStream fis = new FileInputStream(new File("src\\main\\java\\tcp\\hello.jpg"));

        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        // 图片传输完成，不再输出数据
        socket.shutdownOutput();

        // 接收服务器端返回的信息，并显示到控制台上
        InputStream is = socket.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] bytes = new byte[20];
        int length;
        while ((length = is.read(bytes)) != -1) {
            bos.write(bytes, 0, length);
        }

        System.out.println(bos);

        bos.close();
        fis.close();
        os.close();
        socket.close();

    }


    @Test
    public void server() throws IOException{
        // 1.创建服务端socket
        ServerSocket ss = new ServerSocket(8888);
        // 2.接受客户端的数据
        Socket socket= ss.accept();
        // 3.接受客户端数据流
        InputStream is = socket.getInputStream();

        FileOutputStream fos = new FileOutputStream("src\\main\\java\\tcp\\hello1.jpg");

        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }

        // 反馈接受成功信息给客户端
        OutputStream os = socket.getOutputStream();
        os.write("图片传输已完成".getBytes());

        os.close();
        fos.close();
        is.close();
        ss.close();


    }
}
