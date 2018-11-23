package com.my.test.designpatterns.decoratororwrapper;

public class Client {

    /**
     * 这个模式其实打包工具可以使用，对象的包装，灵活性更强，（TODO 需要理解跟 proxy模式、责任链模式、的区别）
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        ConcreteComponent concreteComponent = new ConcreteComponent();
        concreteComponent.doSomething();
        System.out.println("------- 华丽分割线 --------");
        new ConcreteDecorator1(concreteComponent).doSomething();
        System.out.println("------- 华丽分割线 --------");
        new ConcreteDecorator2(concreteComponent).doSomething();
        System.out.println("------- 华丽分割线 --------");
        new ConcreteDecorator2(new ConcreteDecorator1(concreteComponent)).doSomething();
    }
}
