package override;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Description
 * @Author qi
 * @Date 2020/4/30 11:22
 * @ClassName OverrideException
 **/

/*
    方法重写规则一：子类重写的方法抛出的异常类型不大于父类被重写的方法抛出的异常类型。
                  如果父类中被重写的方法没有使用throws处理异常，则子类重写的方法也不能使用throws，意味着如果子类方法中有异常，必须使用try-catch-finally处理
 */

public class OverrideException {

}

class SupperClass {

    public void method() throws IOException {

    }
}

class SubClass extends SupperClass {

    @Override
    public void method() throws FileNotFoundException {

    }
}
