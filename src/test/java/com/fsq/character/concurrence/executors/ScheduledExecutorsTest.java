package com.fsq.character.concurrence.executors;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * Created by fan on 19/2/19.
 */
public class ScheduledExecutorsTest {
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

    @Test
    public void testDelayTime() throws InterruptedException {
        IntStream.range(0,10).forEach(new IntConsumer() {
            @Override
            public void accept(int value) {
                scheduledExecutorService.schedule((Callable) () -> {
                            Thread.sleep(3000);
                            System.out.println("Executed!");
                            return "Called!";
                        },
                        1,//前一次执行结束到下一次执行开始的间隔时间（间隔执行延迟时间）
                        TimeUnit.SECONDS);
            }
        });
//        scheduledExecutorService.shutdown();

//        Thread.sleep(50000);
    }
}
