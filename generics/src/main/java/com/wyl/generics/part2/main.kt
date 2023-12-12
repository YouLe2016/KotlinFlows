package com.wyl.generics.part2

import com.wyl.generics.part1.Point

/**
 * 类型绑定
 * 通配符
 */
fun main() {
    // sample1()
    // sample2()
    extends()
    // `super`()
}

private fun countEmployee(list: MutableList<out Employee>): Int {
    return list.sumOf { it.countLegs() }
}

fun extends() {
    val list: MutableList<Manager> = mutableListOf()
    list.add(CEO())
    list.add(Manager())
    // 编译错误
    // list.add(Employee())
    countEmployee(list)
}

fun `super`() {

}

fun sample2() {
    // 四个类型，怎么合成一个类型呢？
    var point = Point(1.2, 2.6)
    val point2 = Point(1.2f, 2.6f)
    val point3 = Point(1, 2)
    val point4 = Point(1L, 2L)

    var point5: Point<*> = point
    point5 = point2
    point5 = point3
    point5 = point4

    println(point5)
}

/**
 * 类型绑定的作用
 * 1、限定调用者使用类型
 * 2、帮助编译器知道更多的信息
 */
fun sample1() {
    // part1.sample2()
    //下面这行编译报错
    // val point2 = Point2('a', 'b')
    val point2 = Point2(1.2, 2.6)
    val point3 = Point2(1.8, 3.0)
    if (point2 < point3) {
        println("point2 < point3")
    } else if (point2 > point3) {
        println("point2 > point3")
    } else {
        println("point2 == point3")
    }
}