package com.wyl.generics

fun main() {
    println(min(1, 2, 3))
    Int
}

//添加上extends Comparable之后，就可以Comparable里的函数了
fun <T : Comparable<T>> min(vararg a: T): T? {
    if (a.isEmpty()) return null
    var smallest: T = a[0]
    for (item in a) {
        if (smallest > item) {
            smallest = item
        }
    }
    return smallest
}


