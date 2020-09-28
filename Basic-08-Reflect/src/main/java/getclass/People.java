package getclass;

import java.io.Serializable;

/**
 * @Description
 * @Author qi
 * @Date 2020/9/28 14:28
 **/

public class People<T> implements Serializable {

    private char gender;
    public double weight;

    private void breath() {
        System.out.println("breathing...");
    }

    public void eat() {
        System.out.println("eating...");
    }
}