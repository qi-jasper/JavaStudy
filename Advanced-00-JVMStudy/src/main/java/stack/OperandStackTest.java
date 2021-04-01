package stack;

/**
 * @Description
 * @Author qi
 * @Date 2021/3/31 14:21
 * @ClassName OperandStackTest
 **/
public class OperandStackTest {
    public void addOperationTest() {
        byte i = 15;
        int j = 18;
        int k = i+ j;
    }

    public int sum() {
        int i = 10;
        int j = 8;
        int k = i + j;

        return k;
    }

    public void getSum() {
        int i = sum();
        int j = 10;
    }

    /**
     * i++ 和 ++i
     */
    public void addAddOperation() {
        // 第一类问题
        int i1 = 1;
        i1++;
        System.out.println("After i1++: " + i1);

        int i2 = 2;
        ++i2;
        System.out.println("After ++i2: " + i2);

        // 第二类问题
        int i3 = 3;
        int i4 = i3++;
        System.out.println("i4: " + i4);

        int i5 = 5;
        int i6 = ++i5;
        System.out.println("i6: " + i6);

        // 第三类问题
        int i7 = 7;
        i7 = i7++;
        System.out.println("i7: " + i7);

        int i8 = 8;
        i8 = ++i8;
        System.out.println("i8: " + i8);

        // 第四类问题
        int i9 = 9;
        int i10 = i9++ + ++ i9;
        System.out.println("i10: " + i10);

    }

    public static void main(String[] args) {
        OperandStackTest op = new OperandStackTest();
        op.addAddOperation();
    }
}