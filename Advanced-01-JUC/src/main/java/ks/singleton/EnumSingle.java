package ks.singleton;

/**
 * @Description
 * @Author qi
 * @Date 2020/5/12 11:11
 * @ClassName EnumSingle
 **/

// 枚举本身也是一个Class类
public enum EnumSingle {

    INSTANCE;

    public EnumSingle getInstance() {
        return INSTANCE;
    }
}


class Test {

    public static void main(String[] args) {
        EnumSingle instance1 = EnumSingle.INSTANCE;
    }
}
