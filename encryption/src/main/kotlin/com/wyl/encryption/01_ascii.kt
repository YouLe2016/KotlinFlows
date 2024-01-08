package com.wyl.encryption

fun a01_ascii() {
    val str = "I Love you"
    str.toCharArray()
        .joinToString { it.code.toString() }
        .let(::println)
}