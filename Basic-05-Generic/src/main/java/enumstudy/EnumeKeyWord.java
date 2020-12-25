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
enum EnumSeason {

    // 1.提供当前枚举类的对象，多个对象之间用“,”隔开，最后一个对象用“;”结束，而且一定要写在最前面！
    SPRING("春天", "春暖花开"),
    SUMMER("夏天", "夏日炎炎"),
    AUTUMN("秋天", "秋高气爽"),
    WINTER("冬天", "冰天雪地");

    private String seasonName;
    private String seasonDesc;

    EnumSeason(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }
}

class EnumTest {
    public static void main(String[] args) {

        EnumSeason spring = EnumSeason.SPRING;

        // 获得枚举类 Season 的父类
        System.out.println("枚举类 Season 的父类是：" + EnumSeason.class.getSuperclass());

        System.out.println("**************************");

        // values():返回枚举类型的对象数组，该方法可以很方便地遍历所有的枚举值
        EnumSeason[] values = EnumSeason.values();
        for (int i = 0; i < values.length; i++) {
            System.out.println("values() 方法返回枚举类型的对象数组，该方法可以很方便地遍历所有的枚举值：" + values[i]);
        }

        System.out.println("**************************");

        // valueOf(String str):返回枚举类中对象名为传入的 str 的对象
        System.out.println("valueOf() 方法返回枚举类中对象名为传入的 str 的对象：" + EnumSeason.valueOf("SUMMER"));

        System.out.println("**************************");

        // toString()：只能打印出对象名 打印SPRING
        System.out.println("Sring 的 toString() 方法只能打印出对象名：" + spring.toString());

        System.out.println("**************************");

        // 使用 getXXX() 获取对象的值
        System.out.println("Spring 的名称是：" + EnumSeason.SPRING.getSeasonName() + "；Spring 的描述是：" + EnumSeason.SPRING.getSeasonDesc());

    }
}
