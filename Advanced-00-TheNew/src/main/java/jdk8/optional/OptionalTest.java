package jdk8.optional;

import org.junit.Test;

import java.util.Optional;

/**
 * @Description Optional 类的测试
 * @Author qi
 * @Date 2020/10/20 15:15
 * @ClassName OptionalTest
 **/

public class OptionalTest {


    @Test
    public void test1() {
        Girl girl = new Girl();
        // 正常
        Optional<Girl> optionalGirl = Optional.of(girl);
        girl = null;
        // 报错
        Optional<Girl> optionalGirl2 = Optional.of(girl);
    }

    @Test
    public void test2() {
        Girl girl = new Girl();
        Optional<Girl> optionalGirl = Optional.ofNullable(girl);
        girl = null;
        // 可以为空，不会报异常
        System.out.println(optionalGirl);
    }


    public String getGirlName(Boy boy) {
        return boy.getGirl().getName();
    }

    public String getGirlName2(Boy boy) {
        Optional<Boy> boyOptional = Optional.ofNullable(boy);
        Boy boy1 = boyOptional.orElse(new Boy(new Girl("qi")));
        Girl girl = boy1.getGirl();

        Optional<Girl> girlOptional = Optional.ofNullable(girl);
        Girl girl1 = girlOptional.orElse(new Girl("liu"));

        return girl1.getName();
    }

    @Test
    public void test3() {
        Boy boy = new Boy();
        boy = null;
        // 空指针异常
        System.out.println(getGirlName(boy));

        /**
         * 使用 Optional 测试
         */
        System.out.println(getGirlName2(boy));
    }
}
