package mooc.immutable;

/**
 * @Description
 * @Author qi
 * @Date 2020/8/5 10:36
 * @ClassName FinalVariableDemo
 **/

public class FinalVariableDemo {

    /**
     * 普通变量赋值
     */
    // 第一种赋值方式，直接赋值
    private final int NUM = 6;

    // 第二种赋值方式，构造器赋值
    private final int NUM2;
    public FinalVariableDemo(int a) {
        NUM2 = a;
    }

    // 第三种赋值方式，代码块赋值
    private final int NUM3;
    {
        NUM3 = 7;
    }


    /**
     * static 变量赋值
     */
    // 第一种赋值方式，直接赋值
    private static final int A = 6;

    // 第二种赋值方式，静态代码块赋值
    private static final int B;
    static {
        B = 7;
    }

    void testFinal() {
        // 没有赋值，但不会报错。但是使用前一定要赋值
        final int X;
    }
}
