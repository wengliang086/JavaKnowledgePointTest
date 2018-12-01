package com.my.test.java8.lambda;

@FunctionalInterface
public interface MyPredicate<T> {
    boolean test(T t);

//    boolean test2(T t);
}
