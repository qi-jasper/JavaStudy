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

    @MyAnnotation
    public void info() {
        System.out.println("This is a man...");
    }

    public int compareTo(String o) {
        return 0;
    }
}