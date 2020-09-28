package handle;

import org.junit.Test;

/**
 * @Description
 * @Author qi
 * @Date 2020/4/30 11:35
 * @ClassName ThrowTest
 **/

/*
    手动生成异常对象，并抛出
 */

public class ThrowTest {

    @Test
    public void throwTest() {
        try {
            Student student = new Student();
            student.regist(-10);
            System.out.println(student);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

class Student {

    private int id;

    public void regist(int id) throws Exception {
        if (id > 0) {
            this.id = id;
        } else {
            // 手动抛出异常
//            throw new RuntimeException("你输入的数据非法！");
            throw new Exception("你输入的数据非法！");
        }
    }
}
