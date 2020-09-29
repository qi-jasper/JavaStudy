package getclass;

/**
 * @Description
 * @Author qi
 * @Date 2020/9/28 14:31
 **/
@MyAnnotation(value = "hi")
public class Man extends People<String> implements MyInterface, Comparable<String> {

    private String name;
    int age;
    public int id;

    public Man() {

    }

    @MyAnnotation(value = "qi")
    private Man(String name) {
        this.name = name;
    }

    @Override
    @MyAnnotation
    public void info() {
        System.out.println("This is a man...");
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }

    public static void staticMethod() {
        System.out.println("this is static method");;
    }
}