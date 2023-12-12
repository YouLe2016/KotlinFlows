package com.wyl.generics.part1

data class Point<T>(
    var x: T,
    var y: T,
) {
    fun fetchX(): T {
        return x
    }

    fun saveX(x: T) {
        this.x = x
    }
}