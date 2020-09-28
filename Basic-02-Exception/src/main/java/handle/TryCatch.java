package handle;

import org.junit.Test;

/**
 * @Description
 * @Author qi
 * @Date 2020/4/30 10:01
 * @ClassName TryCatch
 **/


/*
  异常处理：抓抛模型

  过程一：抛：程序正常执行过程中出现异常，就会在异常代码处生成一个对应异常类的对象
            并将此对象抛出，一旦抛出对象以后，后续代码不再执行

  过程二：抓：
        1.try-catch-finally，出现异常，则和catch匹配，若匹配到相关西昌，执行对应的处理方式，
        后续不再执行catch结构，若有finally结构，finally一定执行
        2.catch中的异常类型没有子父类关系，则谁声明在上谁声明在下都无所谓
        3.若catch中的异常类型有子父类的关系，则子类一定要声明在父类的上面，否则报错
        4.try-catch-finally处理异常时，使得程序在编译时不会报错，但是运行时仍然可能报错
            try {
                // 可能出现异常的代码块
            } catch(异常类型1 变量名1) {
                // 处理方式1
            } catch(异常类型2 变量名2) {
                // 处理方式2
            } catch(异常类型3 变量名3) {
                // 处理方式3
            }
            ...
            finally {
                // 一定会执行的代码，不一定非要写
            }
*/
public class TryCatch {

    @Test
    public void tryCatchTest() {

        String str = "hello";
        try {
            int num = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            // 使用System.out.println自定义打印异常信息
            System.out.println("出现数值转换异常！");

            // 调用getMessage()方法，打印异常信息
            String message = e.getMessage();
            System.out.println(message);

            // 调用printStackTrace()方法，打印异常信息，更常用
            e.printStackTrace();
        } finally {
            System.out.println("不论是否出现异常都会执行的代码块！");
        }
    }

}
