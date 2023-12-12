package com.wyl.reflect

private class MyClass {

}

fun main() {
    sample1()
}


/*
class com.wyl.reflect.MyClass
class com.wyl.reflect.MyClass
class com.wyl.reflect.MyClass
MyClass
com.wyl.reflect.MyClass
com/wyl/reflect/MyClass
com/wyl/reflect/MyClass.class
 */
fun sample1() {
    val myClass = MyClass()
    println(myClass.javaClass)
    println(myClass.javaClass.kotlin)
    println(myClass::class)
    println(myClass.javaClass.kotlin.simpleName)
    println(myClass.javaClass.kotlin.qualifiedName)
    println(myClass.javaClass.kotlin.qualifiedName?.replace(".", "/"))
    println(myClass.javaClass.kotlin.qualifiedName?.replace(".", "/") + ".class")
}
