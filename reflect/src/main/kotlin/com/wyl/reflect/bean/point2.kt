package com.wyl.reflect.bean


interface PointSingleInterface<T> {
    var point: T
}

class PointArrayImpl(override var point: IntArray) : PointSingleInterface<IntArray>

class PointArrayImpl2(override var point: Array<Int>) : PointSingleInterface<Array<Int>>