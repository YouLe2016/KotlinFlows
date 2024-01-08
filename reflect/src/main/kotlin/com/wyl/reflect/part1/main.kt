package com.wyl.reflect.part1

import com.wyl.reflect.Animal
import com.wyl.reflect.Cat
import java.lang.reflect.Modifier

fun main() {
    // sample()
    // sample2()
    // sample3()
    // getInterfaces()
//    getModifiers()

}


/*
public
isFinal: false
---------------------
isAbstract: false
isFinal: false
isInterface: false
isNative: false
isPrivate: false
isProtected: false
isPublic: true
isStatic: false
isStrict: false
isSynchronized: false
isTransient: false
isVolatile: false
 */
private fun getModifiers() {
    val animalClass: Class<Animal> = Animal::class.java
    val modifierVal = animalClass.modifiers
    val modifierStr = Modifier.toString(modifierVal)
    println(modifierStr)
    val isFinal = Modifier.isFinal(modifierVal)
    println("isFinal: $isFinal")
    println("---------------------")
    listOf(
        "isAbstract" to Modifier::isAbstract,
        "isFinal" to Modifier::isFinal,
        "isInterface" to Modifier::isInterface,
        "isNative" to Modifier::isNative,
        "isPrivate" to Modifier::isPrivate,
        "isProtected" to Modifier::isProtected,
        "isPublic" to Modifier::isPublic,
        "isStatic" to Modifier::isStatic,
        "isStrict" to Modifier::isStrict,
        "isSynchronized" to Modifier::isSynchronized,
        "isTransient" to Modifier::isTransient,
        "isVolatile" to Modifier::isVolatile,
    ).forEach {
        println("${it.first}: ${it.second.invoke(modifierVal)}")
    }
}

private fun getInterfaces() {
    /*
    interface com.wyl.reflect.IAnimal
    true
    interface com.wyl.reflect.IAnimal
    true
     */
    val animalClass: Class<Animal> = Animal::class.java
    animalClass.interfaces.forEach { println(it) }
    val catClass: Class<Cat> = Cat::class.java
    println(catClass.interfaces.isEmpty())

    catClass.getAllInterface().forEach(::println)
    println(Any::class.java.getAllInterface().isEmpty())
}

/**
 * 获取所有的接口
 */
private fun Class<*>.getAllInterface(): Array<Class<*>> {
    val superClazz: Class<*>? = this.superclass
    return if (superClazz == null) {
        this.interfaces
    } else {
        this.interfaces + superClazz.getAllInterface()
    }
}

private fun sample3() {
//    val catClass: Class<*> = Class.forName("com.wyl.reflect.Cat")
//    println(catClass)
//    val superClass: Class<in Nothing> = catClass.superclass
//    println(superClass)

    /*
    class com.wyl.reflect.Cat
    class com.wyl.reflect.Animal
    class java.lang.Object
    Exception in thread "main" java.lang.NullPointerException: getSuperclass(...) must not be null
     */
    val catClass: Class<Cat> = Cat::class.java
    println(catClass)
    val animalClass: Class<in Cat> = catClass.superclass
    println(animalClass)
    val superclass: Class<in Cat> = animalClass.superclass
    println(superclass)
    val superclass2: Class<in Cat> = superclass.superclass
    println(superclass2)
}

private fun sample2() {
    val animalClass = Animal::class.java
    // 完整类名
    println(animalClass.name)
    // 类名
    println(animalClass.simpleName)
    // 包名
    println(animalClass.packageName)
    println(animalClass.`package`.name)
}

private fun sample() {
    val animal = Animal()

    // 以下都是同一个对象。类只会被加载一次
    val class1 = Animal::class.java
    val class2 = animal.javaClass
    val class3 = Class.forName("com.wyl.reflect.Animal")
    // 不建议使用
    val class4 = Animal::class.java.classLoader.loadClass("com.wyl.reflect.Animal")
    listOf(class1, class2, class3, class4).forEach {
        println(it)
    }
    println(class1 == class2 && class2 == class3 && class3 == class4)
}

