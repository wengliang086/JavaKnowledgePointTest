package com.my.test.designpatterns.decoratororwrapper;

public class ConcreteComponent implements Component {
    @Override
    public void doSomething() {
        System.out.println("ConcreteComponent real do something!");
    }
}
