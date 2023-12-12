package com.wyl.generics.part2

data class Point2<T : Number>(
    var x: T,
    var y: T,
) : Comparable<Point2<T>> {
    fun fetchX(): T {
        return x
    }

    fun saveX(x: T) {
        this.x = x
    }

    override fun compareTo(other: Point2<T>): Int {
        val xD = x.toDouble()
        val xD2 = other.x.toDouble()
        return if (xD > xD2) 1
        else if (xD < xD2) -1
        else 0
    }
}