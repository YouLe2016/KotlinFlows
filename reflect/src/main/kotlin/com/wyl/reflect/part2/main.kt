package com.wyl.reflect.part2

import com.wyl.reflect.Animal
import com.wyl.reflect.bean.GenericPointImpl
import com.wyl.reflect.bean.IntStringPoint
import com.wyl.reflect.bean.Point
import com.wyl.reflect.bean.PointArrayImpl
import com.wyl.reflect.bean.PointArrayImpl2
import com.wyl.reflect.util.printType
import java.lang.reflect.Type
import java.util.Arrays

fun main() {
//    sample1()
//    sample2()
//    typeVariable()
//    typeVariable2()
    testGenericArrayType()

//    newInstance()
}

private fun newInstance() {
    val animalClass = Animal::class.java
    animalClass.constructors.forEach {
        println(it)
//        val animal = it.newInstance("name")
//        println(animal)
    }
    val animal = animalClass.getConstructor(String::class.java).newInstance("name2")
    println(animal)

    val point = Point::class.java
        .getConstructor(Int::class.javaPrimitiveType, Int::class.javaPrimitiveType)
        .newInstance(1, 2)
    println(point)

    val a = Int::class.java
    val b = Int::class.javaPrimitiveType
    println(a)
    println(b)
    println("a == b: ${a == b}")

    println(Arrays.toString(Point::class.java.constructors))
    Point::class.java
        .getConstructor(Any::class.java, Any::class.java)
        .newInstance(1, 2)
        .let { println(it) }

    printType(Point::class.java)

    println(Arrays.toString(IntStringPoint::class.java.constructors))
    val point2 = IntStringPoint::class.java
        .getConstructor(Int::class.java, String::class.java)
        .newInstance(1, "2")
    println(point2)
}

/*
---------------------------
【Class】 typeName: com.wyl.reflect.Animal, name: com.wyl.reflect.Animal
	1、typeParameters: []
	2、componentType: null
===========================
---------------------------
【Class】 typeName: java.awt.Point, name: java.awt.Point
	1、typeParameters: []
	2、componentType: null
===========================
---------------------------
【Class】 typeName: com.wyl.reflect.bean.IntStringPoint, name: com.wyl.reflect.bean.IntStringPoint
	1、typeParameters: []
	2、componentType: null
===========================
---------------------------
【Class】 typeName: com.wyl.reflect.bean.GenericPointImpl, name: com.wyl.reflect.bean.GenericPointImpl
	1、typeParameters: [T]
	【TypeVariable】 typeName: T, name: T
		1、bound: class java.lang.Number
		2、bound: interface java.io.Serializable
	2、componentType: null
===========================
 */
private fun typeVariable2() {
    listOf(
        Animal::class.java,
        Point::class.java,
        IntStringPoint::class.java,
        GenericPointImpl::class.java
    ).forEach(::printType)
}


/*
这里似乎已经获取不到了，我用的JDK17
这里actualTypeArguments获取到的type是
【Class】 typeName: int[], name: [I
【Class】 typeName: java.lang.Integer[], name: [Ljava.lang.Integer;
而非【GenericArrayType】
但是发现了class.componentType()函数
 */
private fun testGenericArrayType() {
    listOf(
        PointArrayImpl(intArrayOf(1, 2, 3)),
        PointArrayImpl2(arrayOf(1, 2, 3)),
        PointArrayImpl3()
    )
        // .asSequence()
        .map {
            it.javaClass.genericInterfaces.toList()
        }.flatten().forEach {
            printType(it)
        }
}

/*
---------------------------
【ParameterizedType】 typeName: com.wyl.reflect.bean.IPoint<T, java.lang.Integer>
1、rawType:
	【Class】 typeName: com.wyl.reflect.bean.IPoint, name: com.wyl.reflect.bean.IPoint
		1、typeParameters: [T, T2]
		【TypeVariable】 typeName: T, name: T
			1、bound: class java.lang.Object
		【TypeVariable】 typeName: T2, name: T2
			1、bound: class java.lang.Object
		2、componentType: null
2、ownerType:
	null
3、actualTypeArguments:
	【TypeVariable】 typeName: T, name: T
		1、bound: class java.lang.Number
		2、bound: interface java.io.Serializable
	【Class】 typeName: java.lang.Integer, name: java.lang.Integer
		1、typeParameters: []
		2、componentType: null
===========================
 */
private fun typeVariable() {
    val genericPointClass: Class<*> = GenericPointImpl::class.java
    genericPointClass.genericInterfaces.forEach {
        printType(it)
    }
}

/*
---------------------------
【ParameterizedType】 typeName: com.wyl.reflect.bean.IPoint<java.lang.Integer, java.lang.String>
1、rawType:
	【Class】 typeName: com.wyl.reflect.bean.IPoint, name: com.wyl.reflect.bean.IPoint
		1、typeParameters: [T, T2]
		【TypeVariable】 typeName: T, name: T
			1、bound: class java.lang.Object
		【TypeVariable】 typeName: T2, name: T2
			1、bound: class java.lang.Object
		2、componentType: null
2、ownerType:
	null
3、actualTypeArguments:
	【Class】 typeName: java.lang.Integer, name: java.lang.Integer
		1、typeParameters: []
		2、componentType: null
	【Class】 typeName: java.lang.String, name: java.lang.String
		1、typeParameters: []
		2、componentType: null
===========================
 */
private fun sample2() {
    IntStringPoint::class.java.genericInterfaces
        .forEach { type ->
            printType(type)
        }
}

/*
【ParameterizedType】 typeName: com.wyl.reflect.bean.Point<java.lang.Integer, java.lang.String>
1、rawType:
	【Class】 typeName: com.wyl.reflect.bean.Point, name: com.wyl.reflect.bean.Point
		1、typeParameters: [T, T2]
		【TypeVariable】 typeName: T, name: T
			1、bound: class java.lang.Object
		【TypeVariable】 typeName: T2, name: T2
			1、bound: class java.lang.Object
		2、componentType: null
2、ownerType:
	null
3、actualTypeArguments:
	【Class】 typeName: java.lang.Integer, name: java.lang.Integer
		1、typeParameters: []
		2、componentType: null
	【Class】 typeName: java.lang.String, name: java.lang.String
		1、typeParameters: []
		2、componentType: null
===========================
 */
private fun sample1() {
    val intPointClass: Class<IntStringPoint> = IntStringPoint::class.java
    val type: Type = intPointClass.genericSuperclass
    printType(type)
}