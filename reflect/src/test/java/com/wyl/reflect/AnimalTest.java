package com.wyl.reflect;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AnimalTest {

    private Animal animal;

    @Before
    public void setUp() throws Exception {
        animal = new Animal("Cat");
    }

    @After
    public void tearDown() throws Exception {
        animal = null;
    }

    @Test
    public void getName() {
        String name = animal.getName();
        System.out.println(name);
        assertEquals("Cat", name);
    }

    @Test
    public void setName() {
        animal.setName("Dog");
        String animalName = animal.getName();
        System.out.println(animalName);
        assertEquals("Dog", animalName);
    }
}