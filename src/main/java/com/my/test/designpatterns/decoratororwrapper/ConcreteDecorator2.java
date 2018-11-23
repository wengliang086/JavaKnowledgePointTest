package com.my.test.designpatterns.decoratororwrapper;

public class ConcreteDecorator2 extends Decorator {

    public ConcreteDecorator2(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        System.out.println("ConcreteDecorator2 start");
        super.doSomething();
        System.out.println("ConcreteDecorator2 end");
    }
}
