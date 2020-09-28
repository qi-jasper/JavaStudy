package fileIO;

/**
 * @Description
 * @Author qi
 * @Date 2020/5/1 9:54
 * @ClassName FileReaderWriter
 **/

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 抽象基类         节点流/文件流         缓冲流（处理流的一种
 * InputStream     FileInputStream     BufferedInputStream
 * OutputStream    FileOutputStream    BufferedOutputStream
 * Reader          FileReader          BufferedReader
 * Writer          FileWriter          BufferedWriter
 */

public class FileReaderWriter {


    /**
     * tips:
     * 1.read()：返回读入的一个字符到，如果达到文件的末尾，返回-1
     * 2.异常的处理：为了保证流资源一定可以执行关闭操作，需要使用try-catch-finally处理
     * 3.读入的文件一定要存在，否则就会报FileNotFoundException
     */
    @Test
    public void readTest() {

        FileReader fileReader = null;

        try {
            // Test里的路径相对于当前的module，若是写在main方法中，路径则是相较于当前的工程
            // 1.实例化File类的对象，指明要操作的文件
            File file = new File("src/main/java/fileIO/hello.txt");

            // 2.提供具体的流
            fileReader = new FileReader(file);

            // 3.数据的读入：将存储设备上的数据读入内存
            int data;
            while ((data = fileReader.read()) != -1) {
                // 不使用println进行换行
                System.out.print((char) data);
                data = fileReader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 4.流的关闭操作
                if (fileReader != null) fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    
    
    // 对read()操作的升级：使用read的重载方法。！！！测试结果失败！！！
    @Test
    public void fileReaderTest() {

        FileReader fr = null;

        try {
            // 1.File类的实例化
            File file = new File("src\\main\\java\\fileIO\\hello.txt");

            // 2.FileReader流的实例化
            fr = new FileReader(file);

            // 3.读入操作（此处升级）
            // read(char[] cubf)：返回每次读入cbuf数组中的字符的个数，如果到达文件末尾，则返回-1
            char[] cbuf = new char[5];

            //int len = fr.read(cbuf);
            //while (len != -1) {
            int len;
            while ((len = fr.read(cbuf)) != -1) {
                // 错误的写法
                // for (int i = 0; i < cbuf.length; i++) System.out.print(cbuf[i]);

                for (int i = 0; i < len; i++) System.out.print(cbuf[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 4.资源关闭
                if (fr != null) fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    // 写出操作：从内存写出数据到硬盘文件
    @Test
    public void fileWriterTest() {

        FileWriter fw = null;

        try {
            // 1.提供File类的对象，指明写出到的文件
            File file = new File("src\\main\\java\\fileIO\\hello.txt");

            // 2.提供FileWriter的对象，用于数据的写出，可以设置append参数为true，表示在原有文件上追加。new FileWriter(file, true)
            fw = new FileWriter(file);

            // 3.写出操作
            // 覆盖写，将原有文件内容全部覆盖掉，原有文件可以不存在，若不存在，就会自动创建文件。
            fw.write("Hello China!");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 4.资源关闭
                if (fw != null) fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    // 先读入，后写出操作，相当于复制操作
    @Test
    public void fileReaderWriterTest() throws IOException{
        // 1.创建File类的对象，指明读入和写出的文件
        File srcFile = new File("src\\main\\java\\fileIO\\srcFile.txt");
        File outFile = new File("src\\main\\java\\fileIO\\outFile.txt");

        // 2.创建输入流和输出流的对象
        FileReader fr = new FileReader(srcFile);
        FileWriter fw = new FileWriter(outFile, true);

        // 3.数据的读入和写出操作
        char[] cbuf = new char[5];
        // 记录每次读入到cbuf数组中的字符的个数
        int len;
        while ((len = fr.read(cbuf)) != -1) {
            // 每次写出len个字符
            fw.write(cbuf, 0, len);
        }

        // 4.关闭资源
        fw.close();
        fr.close();

    }
}
