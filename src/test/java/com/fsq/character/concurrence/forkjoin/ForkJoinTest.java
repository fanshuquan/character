package com.fsq.character.concurrence.forkjoin;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

/**
 * Created by fan on 19/2/18.
 */
public class ForkJoinTest {
    @Test
    public void testRecursiveAction(){
        MyRecursiveAction myRecursiveAction = new MyRecursiveAction(24);

        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        forkJoinPool.invoke(myRecursiveAction);
    }

    @Test
    public void testRecursiveTask(){
        MyRecursiveTask myRecursiveTask = new MyRecursiveTask(128);

        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        long mergedResult = forkJoinPool.invoke(myRecursiveTask);

        System.out.println("mergedResult = " + mergedResult);

    }
}
