package file;

import org.junit.Test;

import java.io.File;

/**
 * @Description
 * @Author qi
 * @Date 2020/4/30 19:56
 * @ClassName FileMethods
 **/

public class FileMethods {


    /**
     * File类的常用方法
     * getAbsolutePath()：获取绝对路径
     * getPath()：获取路径
     * getName()：获取名称
     * getParent()：获取上层文件目录路径，若无返回null
     * Length()：获取文件长度（字节数），不能获取目录长度
     * LastModified()：获取最后一次的修改时间，毫秒值
     * list()：获取指定目录下的所有文件或者文件目录的名称数组（当前文件路径下所有的文件名）
     * listFiles()：获取指定目录下的所有文件或者文件目录的File数组（当前文件路径下所有文件的详细路径）
     *
     * boolean isDirectory()：判断是否是文件目录
     * boolean isFile()：判断是否是文件
     * boolean exists()：判断是否存在
     * boolean canRead()：判断是否可读
     * boolean canWrite()：判断是否可写
     * boolean isHidden()：判断是否隐藏
     *
     * boolean createNewFile()：创建文件，若文件存在，则不创建，返回false
     * boolean mkdir()：创建文件目录，如果此文件目录存在，则不创建；如果此文件目录的上层目录不存在，也不创建
     * boolean mkdirs()：创建文件目录，如果上层文件目录不存在，则一并创建
     * boolean delete()：删除文件或文件夹（Java中的删除不走回收站，如果要删除一个文件目录，请注意该文件目录中不能包含文件或者文件目录
     * 
     * 
     * boolean renameTo(File dest)：把文件重命名为指定的文件路径
     */
    @Test
    public void fileMethodTest() {
        File file = new File("hello.txt");

        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getPath());
        System.out.println(file.getName());
        System.out.println(file.getParent());
        System.out.println(file.length());
        System.out.println(file.list());


        File file1 = new File("src/main/java/file/hello.txt");
        System.out.println(file1.isDirectory());
        System.out.println(file1.isFile());
        System.out.println(file1.exists());
    }

    @Test
    public void fileRenameTest() {
        /**
         * boolean renameTo(File dest)：把文件重命名为指定的文件路径
         * 比如：file1.renameTo(file2)为例
         * 要想保证返回结果为 true，需要保证 file1 是存在的，file2 是不存在的
         */
        File file1 = new File("hello.txt");
        File file2 = new File("D:\\hi.txt");

        /**
         * 执行后会发生：file1 路径下的 hello.txt 变为不存在，
         * 在 D:\\ 目录下会生成一个 hi.txt 文件，里面的内容是 file1 中 hello.txt 的内容
         */
        file1.renameTo(file2);
    }


}
