package com.my.test.objectmemory;

public class Car {

    private int cId = 100;
    private Wheel[] wheels;
    private Engine engine;

    public Car(int cId, Wheel[] wheels, Engine engine) {
        this.cId = cId;
        this.wheels = wheels;
        this.engine = engine;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public Wheel[] getWheels() {
        return wheels;
    }

    public void setWheels(Wheel[] wheels) {
        this.wheels = wheels;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
