package com.wyl.hilt.service

import com.wyl.hilt.di.anno.Apple
import javax.inject.Inject

//class Fruit @Inject constructor(@Apple private val name: String) {
class Fruit(private val name: String) {
    fun name(): String {
        return name
    }
}