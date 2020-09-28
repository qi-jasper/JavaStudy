package enumstudy;

/**
 * @Description 枚举类
 * @Author qi
 * @Date 2020/5/31 13:41
 * @ClassName SeasonTest
 **/

/**
 * 使用enum定义的枚举类继承于java.lang.Enum父类
 *
 * Enum类中常用的方法
 * values():返回枚举类型的对象数组，该方法可以很方便地遍历所有的枚举值
 * valueOf(String str):可以把一个字符串转为对应的枚举类对象，要求字符串必须是枚举类对象的名字，不然会有"IllegalArgumentException"
 * toString():返回当前枚举类对象常量的名称
 */
enum Season {

    // 1.提供当前枚举类的对象，多个对象之间用“,”隔开，最后一个对象用“;”结束，而且一定要写在最前面！
    SPRING("春天"),
    SUMMER("夏天"),
    AUTUMN("秋天"),
    WINTER("冬天");

    private String season;
    Season(String season) {
        this.season = season;
    }

}

class EnumTest {
    public static void main(String[] args) {

        Season spring = Season.SPRING;

        // 获得枚举类Season的父类
        /*System.out.println(Season.class.getSuperclass());*/

        // values():返回枚举类型的对象数组，该方法可以很方便地遍历所有的枚举值
        Season[] values = Season.values();
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }

        System.out.println("**************************");

        // valueOf(String str):返回枚举类中对象名为传入的str的对象
        System.out.println(Season.valueOf("SUMMER"));

        System.out.println("**************************");

        // toString()
        System.out.println(spring.toString());  // 打印SPRING

    }
}
