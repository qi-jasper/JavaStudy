package enumstudy;

/**
 * @Description
 * @Author qi
 * @Date 2020/12/25 19:45
 * @ClassName DefinedEnum
 **/
class DefinedSeason {

    // 1.声明 Season 对象的属性：private
    private final String seasonName;
    private final String seasonDesc;

    // 2.私有化类的构造器，并给对象属性赋值
    private DefinedSeason(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    // 3.提供当前枚举类的多个对象
    public static final DefinedSeason SPRING = new DefinedSeason("春天", "春暖花开");
    public static final DefinedSeason SUMMER = new DefinedSeason("夏天", "夏日炎炎");
    public static final DefinedSeason AUTUMN = new DefinedSeason("秋天", "秋高气爽");
    public static final DefinedSeason WINTER = new DefinedSeason("冬天", "冰天雪地");

    // 4.获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    // 还可以提供 toString() 方法
    @Override
    public String toString() {
        return "DefinedSeason{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}

public class DefinedEnum {
    public static void main(String[] args) {
        DefinedSeason spring = DefinedSeason.SPRING;
        DefinedSeason summer = DefinedSeason.SUMMER;
        DefinedSeason autumn = DefinedSeason.AUTUMN;
        DefinedSeason winter = DefinedSeason.WINTER;

        System.out.println(spring.getSeasonName());
        System.out.println(summer.getSeasonDesc());
    }
}