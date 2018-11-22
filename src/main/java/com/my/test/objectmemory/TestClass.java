package com.my.test.objectmemory;

public class TestClass {

    public static void main(String[] args) throws InterruptedException {
        Wheel[] wheels = new Wheel[4];
        for (int i = 0; i < 4; i++) {
            wheels[i] = new Wheel(i);// 32
        }
        Engine engine = new Engine();// 28
        Car c = new Car(101, wheels, engine);// 36

        NullClass nullClass = new NullClass();// 16
        IntClass intClass = new IntClass();// 20
        StringClass stringClass = new StringClass();// 24
        RefClass1 refClass1 = new RefClass1();// 24
        RefClass2 refClass2 = new RefClass2();// 24
        Thread.sleep(1000 * 1000);
    }
}
