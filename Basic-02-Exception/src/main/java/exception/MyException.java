package exception;

/**
 * @Description
 * @Author qi
 * @Date 2020/4/30 11:47
 * @ClassName MyException
 **/

/*
    自定义异常类
        1.继承于现有的异常结构：RuntimeException，Exception
        2.提供全局常量：serialVersionUID
        3.提供重载的构造器
 */

public class MyException extends Exception {

    // 一般把msg重写
    public MyException(String msg) {
        super(msg);
    }
}
