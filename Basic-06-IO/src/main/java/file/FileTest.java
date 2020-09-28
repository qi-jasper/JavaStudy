package file;

/**
 * @Description
 * @Author qi
 * @Date 2020/4/30 18:00
 * @ClassName FileTest
 **/

import org.junit.Test;

import java.io.File;

/**
 * 1.File类的一个对象，代表一个文件或者一个文件目录（文件夹）
 * 2.File类声明在java.io包下
 */
public class FileTest {
    /**
     * 1.创建一个File类的实例
     * 2.相对路径：相对某个路径下所指明的路径
     *   绝对路径：完整的路径，包含盘符在内的文件目录路径
     */
    @Test
    public void fileCreateTest() {

        // 构造器1：文件及路径
        File file1 = new File("hello.txt");
        System.out.println(file1);

        // 构造器2：父目录 + 子目录
        File file2 = new File("/usr/local", "hello");
        System.out.println(file2);

        // 构造器3：file + 文件名
        File file3 = new File(file2, "hello.txt");
        System.out.println(file3);
    }



}
