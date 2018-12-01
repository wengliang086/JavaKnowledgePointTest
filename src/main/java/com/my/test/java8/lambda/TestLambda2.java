package com.my.test.java8.lambda;

import org.junit.Test;

import java.util.Comparator;

public class TestLambda2 {

    @Test
    public void test01() {
        Comparator<Integer> comparator = (a, b) -> Integer.compare(a, b);
    }

}
