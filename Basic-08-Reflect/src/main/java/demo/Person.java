package demo;

/**
 * @Description
 * @Author qi
 * @Date 2020/9/27 11:38
 **/

public class Person {
    public String name;
    public int age;

    private int number;


    public Person() {

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private Person(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", number=" + number +
                '}';
    }

    public void show() {
        System.out.println("This is a person");
    }

    private String print(String name) {
        this.name = name;
        System.out.println("这里是私有方法print()，输出name属性的值为：" + this.name);
        return this.name;
    }
}