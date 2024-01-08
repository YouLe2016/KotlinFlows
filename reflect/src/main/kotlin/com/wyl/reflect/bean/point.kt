package com.wyl.reflect.bean

import java.io.Serializable

open class Point<T, T2>(
    var x: T,
    var y: T2,
)

interface IPoint<T, T2> {
    var x: T
    var y: T2
}

class IntStringPoint(x: Int, y: String) : Point<Int, String>(x, y), IPoint<Int, String>

class GenericPointImpl<T>(
    override var x: T,
    override var y: Int,
) : IPoint<T, Int> where T : Number, T : Serializable
