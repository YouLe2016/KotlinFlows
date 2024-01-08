package com.wyl.reflect.part2;

import com.wyl.reflect.bean.PointArrayImpl;
import com.wyl.reflect.bean.PointArrayImpl2;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Main {
    public static void main(String[] args) {
        Class<?> clazz = PointArrayImpl.class;
        Class<?> clazz2 = PointArrayImpl2.class;
        Class<?> clazz3 = PointArrayImpl3.class;
        Type[] interfaces = clazz3.getGenericInterfaces();
        for (Type type:interfaces){
            if (type instanceof ParameterizedType) {
                ParameterizedType pt = (ParameterizedType) type;
                Type[] actualArgs = pt.getActualTypeArguments();
                for (Type arg:actualArgs){
                    System.out.println("类型："+ (arg instanceof GenericArrayType));
                    if (arg instanceof GenericArrayType){
                        GenericArrayType arrayType = (GenericArrayType)arg;
                        Type comType = arrayType.getGenericComponentType();
                        Class<?> typeClass = (Class)comType;
                        System.out.println("数组类型为："+typeClass.getName());
                    }
                }
            }
        }
    }
}
