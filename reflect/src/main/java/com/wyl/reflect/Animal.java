package com.wyl.reflect;

import java.util.function.Supplier;

public class Animal implements IAnimal {
    public Animal() {
    }

    public Animal(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                '}';
    }


}