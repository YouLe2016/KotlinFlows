package com.wyl.hilt.service

import javax.inject.Inject

//class Fruit @Inject constructor(private val name: String) {
class Fruit(private val name: String) {
    fun name(): String {
        return name
    }
}