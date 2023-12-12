package com.wyl.generics;

import org.jetbrains.annotations.Nullable;

public class Main2 {
    public <T> T convert(Number a) {
        return (T) a;
    }

    public static void main(String[] args) {
        Main2 main2 = new Main2();
        Integer integer = main2.convert(1);
        // class java.lang.Integer
        System.out.println(integer.getClass());
    }

    @Nullable
    public static <T extends Comparable<T>> T min(T... a) {
        if (a == null || a.length == 0) {
            return null;
        }
        T smallest = a[0];
        for (int i = 1; i < a.length; i++) {
            if (smallest.compareTo(a[i]) > 0) {
                smallest = a[i];
            }
        }
        return smallest;
    }
}
