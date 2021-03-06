package com.fsq.character.concurrence.steam;

import org.junit.Test;

import java.util.stream.IntStream;

/**
 * Created by fan on 19/2/13.
 * reference:
 * https://www.ibm.com/developerworks/cn/java/j-lo-java8streamapi/
 */
public class SteamTest {


    @Test
    public void createSteam(){
        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.rangeClosed(1, 3).forEach(System.out::println);
    }
}
