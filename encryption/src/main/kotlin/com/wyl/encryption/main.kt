package com.wyl.encryption

import com.wyl.encryption.utils.SizeUtil

fun main() {
    // a01_ascii()
//    a02_凯撒加密()
//    a04_ByteAndBit()
//    a05_des()
//    a06_aes()
//    a08_Rsa()


    val a = SubA()
    a.test()
    a.test("123")
    println("========a==========")
    a.age = 18.toString()
    a.age = 18.toString()
    println(a.age)

    println("========a2==========")
    val a2 = SubA()
    a2.ageInt = 17
    println(a2.age)

    Runtime.getRuntime().apply {
        println("totalMemory: ${SizeUtil.readableFileSize(totalMemory())}")
        println("freeMemory: ${SizeUtil.readableFileSize(freeMemory())}")
        println("maxMemory: ${SizeUtil.readableFileSize(maxMemory())}")
    }
}

abstract class A {
    abstract fun test(msg: String = "message")
}

class SubA : A() {
    override fun test(msg: String) {
        println(msg)
    }

    var age: String = "0"
}


var SubA.ageInt: Int
    get() {
        return this.age.toInt()
    }
    set(value) {
        this.age = value.toString()
    }