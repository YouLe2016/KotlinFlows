package com.wyl.generics.part1

/*
为什么要有泛型？
泛型的使用
 */
fun main() {
    // sample1()
    // sample2()
}


/**
 * 为什么要有泛型？
 */
private fun sample1() {
    // 一个函数可以接收多个类型，增加程序的拓展性
    val list = listOf(1, 2, 3)
    val list2 = listOf('a', 'b', 'c')
    println(list)
    println(list2)
}

/**
 * 泛型类
 */
private fun sample2() {
    val point = Point(1, 2)
    val point2 = Point('a', 'b')
    println(point)
    println(point2)

    // 还有泛型接口和泛型方法
}
