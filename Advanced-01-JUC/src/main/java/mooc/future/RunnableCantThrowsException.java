package mooc.future;

/**
 * @Description Runnable 无法抛出异常
 * @Author qi
 * @Date 2021/3/24 9:39
 * @ClassName RunnableCantThrowsException
 **/
public class RunnableCantThrowsException {
    public static void main(String[] args) {
        Runnable r = () -> {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}