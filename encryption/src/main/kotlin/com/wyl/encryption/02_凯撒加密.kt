package com.wyl.encryption

import com.wyl.encryption.caesar.Caesar

fun a02_凯撒加密() {
    val str = "什么玩意？"
    val key = 400
    val encrypt = Caesar.encrypt(str, key)
    println(encrypt)
    val decrypt = Caesar.decrypt(encrypt, key)
    println(decrypt)
}