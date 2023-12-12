package com.wyl.generics

import java.io.Serializable

object Test2 {
    fun <T> getName(t: T): String where T : Number, T : Serializable {
        return t.javaClass.simpleName
    }

    fun <T, U : Runnable> foo(a: T, b: U): T where T : Comparable<T>, T : Serializable {
        b.run()
        return a
    }

    // 输出结果
    //Integer
    //hello
    //2
    @JvmStatic
    fun main(args: Array<String>) {
        println(getName(1))
        println(
            foo(2, Runnable { println("hello") })
        )
    }
}