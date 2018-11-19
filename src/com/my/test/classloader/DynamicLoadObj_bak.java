package com.my.test.classloader;

/**
 * 测试自定义类加载器的时候，需要把该类改名，
 * 否则AppClassLoader可以加载到该类，就走不到自定义类加载器中了
 */
public class DynamicLoadObj_bak {

    @Override
    public String toString() {
        printLoaders();
        return "I am need dynamic load object";
    }

    /**
     * 打印类加载器层级
     */
    private void printLoaders() {
        ClassLoader classLoader = getClass().getClassLoader();
        System.out.println(classLoader);
        while (classLoader.getParent() != null) {
            classLoader = classLoader.getParent();
            System.out.println(classLoader);
        }
    }
}
