package ks.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @Description
 * @Author qi
 * @Date 2020/5/11 10:13
 * @ClassName ForkJoinDemo
 **/

/**
 * 求和计算：3000,6000(ForkJoin),9000(Stream并行流)
 */
public class ForkJoinDemo extends RecursiveTask<Long> {
    /**
     * 如何使用ForkJoin
     * 1.forkjoinPool 通过它来执行
     * 2.计算任务：forkjoinPool.execute(ForkJoinTask task)
     * 3.计算类要继承RecursiveTask
     */

    Long start;
    Long end;
    // 临界值
    Long temp = 10000L;
    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if ((end - start) < temp) {
            Long sum = 0L;
            for (Long i = start; i <=end; i++) {
                sum += i;
            }
            System.out.println(sum);

            return sum;

        } else {
            // 分支合并计算,ForkJoin
            long middle = (start + end) / 2;
            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
            // 拆分任务，把任务压入线程队列
            task1.fork();
            ForkJoinDemo task2 = new ForkJoinDemo(middle+1, end);
            task2.fork();

            // 类似于递归
            return task1.join() + task2.join();

        }
    }

}
