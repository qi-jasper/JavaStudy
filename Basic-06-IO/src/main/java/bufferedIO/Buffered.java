package bufferedIO;

/**
 * @Description
 * @Author qi
 * @Date 2020/5/1 13:20
 * @ClassName Buffered
 **/

import org.junit.Test;

import java.io.*;

/**
 * 处理流之一：缓冲流的使用
 * BufferedInputStream
 * BufferedOutputStream
 * BufferedReader
 * BufferedWriter
 *
 * 作用：提高流的读取和写入的速度
 */

public class Buffered {

    // 实现非文本文件的复制
    @Test
    public void BufferedStreamTest() throws IOException {
        // 1.造文件
        File srcFile = new File("src/main/java/bufferedIO/cat.jpg");
        File dstFile = new File("src/main/java/bufferedIO/cat2.jpg");
        // 2.造流
        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(dstFile);
        // 3.造缓冲流
        BufferedInputStream bis = new BufferedInputStream(fis);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        byte[] buffer = new byte[10];
        int len;
        while ((len = bis.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }

        // 4.资源关闭
        // 关闭顺序要求：先关闭外层的流，后关闭内层的流。
        bos.close();
        bis.close();
        // 说明：关闭外层流的同时，内层流也会自动的被关闭，所以内层流的关闭可以省略
        /*fos.close();
        fis.close();*/



    }
}
