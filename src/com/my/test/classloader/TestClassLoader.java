package com.my.test.classloader;

public class TestClassLoader {

    public static void main(String[] args) {
        ClassLoader classLoader = TestClassLoader.class.getClassLoader();
        System.out.println(classLoader);
        while (classLoader.getParent() != null) {
            classLoader = classLoader.getParent();
            System.out.println(classLoader);
        }
    }
}
