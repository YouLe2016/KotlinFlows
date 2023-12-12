package com.wyl.reflect.part1

import com.wyl.reflect.Animal
import com.wyl.reflect.Cat

fun main() {
    // sample()
    // sample2()
    sample3()
}

private fun sample3() {
//    val catClass: Class<*> = Class.forName("com.wyl.reflect.Cat")
//    println(catClass)
//    val superClass: Class<in Nothing> = catClass.superclass
//    println(superClass)

    val catClass: Class<Cat> = Cat::class.java
    println(catClass)
    val superClass: Class<in Cat> = catClass.superclass
    println(superClass)
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

